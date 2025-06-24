package org.k11techlab.framework.selenium.webuitestengine.driverUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.k11techlab.framework.selenium.webuitestbase.ApplicationProperties;
import org.k11techlab.framework.selenium.webuitestengine.configManager.ConfigurationManager;
import org.k11techlab.framework.selenium.webuitestengine.enums.Browsers;
import org.k11techlab.framework.selenium.webuitestengine.logger.Log;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverManager {

    private static final Logger LOG = Logger.getLogger(DriverManager.class.getName());

    private static final boolean headless;
    private static final boolean remote;
    private static final String seleniumHost;
    private static final String seleniumPort;
    private static final String seleniumRemoteUrl;
    private static final String FALLBACK_DRIVER_PATH="drivers/chromedriver.exe";

    private static final String driverDownloadPath = ConfigurationManager.getBundle().getPropertyValue("wdm.targetPath");

    private static WebDriver driver;

    // Session tracking
    private final ThreadLocal<String> sessionId = new ThreadLocal<>();
    private final ThreadLocal<String> sessionBrowser = new ThreadLocal<>();
    private final ThreadLocal<String> sessionPlatform = new ThreadLocal<>();
    private final ThreadLocal<String> sessionVersion = new ThreadLocal<>();

    static {
        seleniumHost = ApplicationProperties.DRIVER_HOST.getStringVal();
        seleniumPort = ApplicationProperties.DRIVER_PORT.getStringVal();
        headless = ApplicationProperties.HEADLESS.getBooleanVal(false);
        remote = ApplicationProperties.REMOTE.getBooleanVal(false);
        seleniumRemoteUrl = "http://" + seleniumHost + ":" + seleniumPort + "/wd/hub";

        // Optional: Set custom download path for WebDriver binaries
        if (driverDownloadPath != null && !driverDownloadPath.isEmpty()) {
            System.setProperty("wdm.targetPath", driverDownloadPath);
        }
    }

    /**
     * Main method to get browser WebDriver instance.
     */
    public static WebDriver getBrowser() {
        Browsers browser;

        if (System.getProperty("browser") == null) {
            String browserName = ApplicationProperties.BROWSER.getStringVal("browser", "Chrome");
            browser = Browsers.browserForName(browserName);
            Log.LOGGER.info("Browser set from config: " + browserName);
        } else {
            browser = Browsers.browserForName(System.getProperty("browser"));
            Log.LOGGER.info("Browser set from system property: " + browser);
        }

        switch (browser) {
            case CHROME:
                return createChromeDriver();
            case FIREFOX:
                return createFirefoxDriver();
            case IE:
            case INTERNETEXPLORER:
                return createInternetExplorerDriver();
            default:
                return createChromeDriver();
        }

    }

    // ------------------- Chrome -------------------

    private static WebDriver createChromeDriver() {
        if (isRemote()) {
            try {
                Log.LOGGER.info("Running Chrome on remote Selenium Grid.");
                return new RemoteWebDriver(new URL(seleniumRemoteUrl), getChromeOptions());
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Grid URL: " + seleniumRemoteUrl, e);
            }
        } else {
            ChromeOptions options = getChromeOptions();

            try {
                // Preferred: Use WebDriverManager to download and configure the driver
                System.setProperty("wdm.targetPath", "target");

                WebDriverManager.chromedriver()
                        .clearDriverCache() // Optional: reset previously cached incorrect versions
                        .architecture(io.github.bonigarcia.wdm.config.Architecture.X64)
                        .setup();

                Log.LOGGER.info("ChromeDriver setup using WebDriverManager.");
                return new ChromeDriver(options);

            } catch (Exception e) {
                Log.LOGGER.info("WebDriverManager failed: " + e.getMessage());
                Log.LOGGER.info("Falling back to local ChromeDriver: " + FALLBACK_DRIVER_PATH);

                File fallback = new File(FALLBACK_DRIVER_PATH);
                if (!fallback.exists()) {
                    throw new RuntimeException("Fallback ChromeDriver not found at: " + fallback.getAbsolutePath());
                }

                // Set manually and launch driver
                System.setProperty("webdriver.chrome.driver", fallback.getAbsolutePath());
                return new ChromeDriver(options);
            }
        }
    }


    public static WebDriver launchChromeBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Optional: headless
        if (headless || "true".equalsIgnoreCase(System.getProperty("headless"))) {
            options.addArguments("--headless");
        }

        try {
            // Try to use WebDriverManager (internet required)
            System.setProperty("wdm.targetPath", "target");
            WebDriverManager.chromedriver()
                    .architecture(io.github.bonigarcia.wdm.config.Architecture.X64) // optional
                    .setup();

            return new ChromeDriver(options);

        } catch (Exception e) {
            System.err.println("WebDriverManager failed: " + e.getMessage());
            System.err.println("Falling back to local ChromeDriver path: " + FALLBACK_DRIVER_PATH);

            // Use fallback ChromeDriver path
            File driverFile = new File(FALLBACK_DRIVER_PATH);
            if (!driverFile.exists()) {
                throw new RuntimeException("Fallback ChromeDriver not found at: " + FALLBACK_DRIVER_PATH);
            }

            System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
            return new ChromeDriver(options);
        }
    }


    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--remote-allow-origins=*"); // optional

        // Optional (fix renderer crashes)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        if (headless || "true".equalsIgnoreCase(System.getProperty("headless"))) {
            options.addArguments("--headless");
        }

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);

        return options;
    }

    // ------------------- Firefox -------------------
    private static WebDriver createFirefoxDriver() {
        if (isRemote()) {
            try {
                Log.LOGGER.info("Running Firefox on remote Selenium Grid.");
                return new RemoteWebDriver(new URL(seleniumRemoteUrl), getFirefoxOptions());
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Grid URL: " + seleniumRemoteUrl, e);
            }
        } else {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(getFirefoxOptions());
        }
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

        if (headless || "true".equalsIgnoreCase(System.getProperty("headless"))) {
            options.setHeadless(true);
        }

        return options;
    }

    // ------------------- Internet Explorer -------------------
    private static WebDriver createInternetExplorerDriver() {
        if (isRemote()) {
            try {
                Log.LOGGER.info("Running IE on remote Selenium Grid.");
                return new RemoteWebDriver(new URL(seleniumRemoteUrl), getInternetExplorerOptions());
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Grid URL: " + seleniumRemoteUrl, e);
            }
        } else {
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver(getInternetExplorerOptions());
        }
    }

    private static InternetExplorerOptions getInternetExplorerOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.ignoreZoomSettings().introduceFlakinessByIgnoringSecurityDomains()
                .enablePersistentHovering().destructivelyEnsureCleanSession();
        return options;
    }

    // ------------------- Utility Methods -------------------

    private static boolean isRemote() {
        String sysProp = System.getProperty("remote");
        return (sysProp != null && sysProp.equalsIgnoreCase("true")) || remote;
    }

    public static HashMap<String, Object> generateDownloadFolderCapability() {
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "./templates/");
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        return prefs;
    }

    // ------------------- Session Info Getters -------------------

    public String getSessionId() {
        return sessionId.get();
    }

    public String getSessionBrowser() {
        return sessionBrowser.get();
    }

    public String getSessionPlatform() {
        return sessionPlatform.get();
    }

    public String getSessionVersion() {
        return sessionVersion.get();
    }
}
