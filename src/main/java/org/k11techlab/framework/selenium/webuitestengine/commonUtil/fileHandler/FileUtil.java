package org.k11techlab.framework.selenium.webuitestengine.commonUtil.fileHandler;

import org.k11techlab.framework.selenium.webuitestengine.logger.Log;
import org.k11techlab.framework.selenium.webuitestengine.commonUtil.StringComparator;
import org.k11techlab.framework.selenium.webuitestengine.commonUtil.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Random;
import java.util.Vector;

public class FileUtil extends FileUtils {
	private static int counter = -1; /* Protected by tmpFileLock */

    private Boolean checkEmptyFile(final File file, long fileLengthLimit) throws IOException {
         if(file.length() < fileLengthLimit && FileUtils.readFileToString(file, "UTF-8").trim().isEmpty()) {
            System.out.println("File is empty: " + file.getName());
            return true;
        }
        else return false;
    }

	public static String saveImageFile(String base64Str, String prefix, String dir) throws Exception {
		byte[] decodedScreenshot = Base64.decodeBase64(base64Str.getBytes());// new
		File file = generateFile(prefix, ".png", dir);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(decodedScreenshot);
		fos.close();
		return file.getCanonicalPath();
	}

	public static String saveImageFile(BufferedImage bImag, String prefix, String dir) throws Exception {
		File file = generateFile(prefix, ".png", dir);
		ImageIO.write(bImag, "png", file);
		return file.getName();
	}

	public static String getContentType(File f) {
		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String ct = fileNameMap.getContentTypeFor(f.getName());
		return StringUtil.isBlank(ct) ? fileTypeMap.getContentType(f) : ct;
	}

	/**
	 * Check and Create a directory if not exist; all non-existent ancestor
	 * directories will automatically created
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean checkCreateDir(String dir) {
		File tdir = new File(dir);
		boolean dirExists = tdir.exists();
		if (!dirExists) {
			dirExists = tdir.mkdirs();
		}
		return dirExists;
	}

	/**
	 * Method to get relative file path Ex: String filePath =
	 * "/var/dto/images/xyz.png"; String root = "/var/dto"; return value will
	 * be "images/xyz.png"
	 * 
	 * @param root
	 * @param filePath
	 * @return
	 */
	public static String getReletivePath(String root, String filePath) {
		String relative = new File(root).toURI().relativize(new File(filePath).toURI()).getPath();
		return relative;
	}

	public static File generateFile(String prefix, String suffix, String dir) throws IOException {
		if (counter == -1) {
			counter = new Random().nextInt() & 0xffff;
		}
		counter++;
		return new File(dir, prefix + Integer.toString(counter) + suffix);
	}

	public static File[] listFilesAsArray(File directory, FilenameFilter filter, boolean recurse) {
		Collection<File> files = listFiles(directory, filter, recurse);
		File[] arr = new File[files.size()];
		return files.toArray(arr);
	}

	public static Collection<File> listFiles(File directory, String fname, StringComparator c, boolean recurse) {
		return listFiles(directory, getFileFilterFor(fname, c), recurse);
	}

	public static File[] listFilesAsArray(File directory, String fname, StringComparator c, boolean recurse) {
		Collection<File> files = listFiles(directory, fname, c, recurse);
		File[] arr = new File[files.size()];
		return files.toArray(arr);
	}

	public static Collection<File> listFiles(File directory, FilenameFilter filter, boolean recurse) {
		// List of files / directories
		Vector<File> files = new Vector<File>();

		// Get files / directories in the directory
		File[] entries = directory.listFiles();

		// Go over entries
		for (File entry : entries) {
			if ((filter == null) || filter.accept(directory, entry.getName())) {
				files.add(entry);
			}

			// If the file is a directory and the recurse flag
			// is set, recurse into the directory
			if (recurse && entry.isDirectory() && !entry.isHidden()) {
				files.addAll(listFiles(entry, filter, recurse));
			}
		}

		// Return collection of files
		return files;
	}

	public static String getBase64String(File f) throws IOException {
		return new String(Base64.encodeBase64(readFileToByteArray(f)));// new
	}

	/**
	 * @param fname
	 *            : file name or part of file name to search
	 * @param comparator
	 *            : comparator to use while filtering file.
	 * @return: FilenameFilter Example:
	 *          <ol>
	 *          <li>getFileFilterFor(".properties", StringComparator.Suffix)
	 *          <li>getFileFilterFor("TC123", StringComparator.Prefix)
	 *          <li>getFileFilterFor("TC123.*.png", StringComparator.RegExp)
	 *          </ol>
	 */
	public static FilenameFilter getFileFilterFor(final String fname, StringComparator comparator) {
		final StringComparator fnamecomparator = null != comparator ? comparator : StringComparator.In;
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return fnamecomparator.compareIgnoreCase(name, fname);
			}
		};

		return filter;
	}

	public static FilenameFilter getFileFilterFor(final String name) {
		return getFileFilterFor(name, null);
	}

	public static FilenameFilter getFileFilterFor(final StringComparator c, final String... qnames) {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				for (String qname : qnames) {
					if (StringUtil.isNotBlank(qname) && c.compareIgnoreCase(name, qname))
						return true;
				}
				return false;
			}
		};

		return filter;
	}

	public static Collection<File> getFiles(File directory, String extension, boolean recurse) {
		// List of files / directories
		Vector<File> files = new Vector<File>();

		// Get files / directories in the directory
		File[] entries = directory.listFiles();

		if(entries != null)
		{
			FilenameFilter filter = getFileFilterFor(extension, StringComparator.Suffix);
			// Go over entries
			for (File entry : entries) {
				if ((filter == null) || filter.accept(directory, entry.getName())) {
					files.add(entry);
				}

				// If the file is a directory and the recurse flag
				// is set, recurse into the directory
				if (recurse && entry.isDirectory()) {
					files.addAll(listFiles(entry, filter, recurse));
				}
			}
		}

		// Return collection of files
		return files;
	}

	public static Collection<File> getFiles(File directory, String name, StringComparator c, boolean recurse) {
		// List of files / directories
		Vector<File> files = new Vector<File>();

		// Get files / directories in the directory
		File[] entries = directory.listFiles();
		FilenameFilter filter = getFileFilterFor(name, c);
		// Go over entries
		for (File entry : entries) {
			if ((filter == null) || filter.accept(directory, entry.getName())) {
				files.add(entry);
			}

			// If the file is a directory and the recurse flag
			// is set, recurse into the directory
			if (recurse && entry.isDirectory()) {
				files.addAll(listFiles(entry, filter, recurse));
			}
		}

		// Return collection of files
		return files;
	}

	/**
	 * @param fname
	 *            : (optional) filename and file extension as two separate args
	 *            or file name with extension..
	 * @return
	 * @throws IOException
	 */
	public static File createTempFile(String... fname) throws IOException {
		return createTempFile(null, fname);
	}

	public static File createTempFile(File dir, String... name) throws IOException {
		String fname = null, ext = null;
		if ((name != null)) {
			if ((name.length == 1) && StringUtil.isNotEmpty(name[0])) {
				// check contains extension?
				int dotSign = name[0].lastIndexOf(".");
				if ((dotSign >= 0) && (dotSign < name[0].length())) {
					fname = name[0].substring(0, dotSign);
					ext = name[0].substring(dotSign);
				} else {
					fname = name[0];
				}
			} else if (name.length > 1) {
				fname = name[0];
				ext = name[1].startsWith(".") ? name[1] : "." + name[1];
			}
		}
		if (StringUtil.isBlank(fname)) {
			fname = StringUtil.createRandomString("Auto");
		}
		File tmpfile = File.createTempFile(fname, ext, dir);
		tmpfile.deleteOnExit();
		return tmpfile;
	}

	public static void appendFile(String fileName, StringBuffer sb) throws IOException {
		FileWriter fileWritter = new FileWriter(fileName, true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(sb.toString());
		bufferWritter.close();
	}

	public static String getExtention(String fileName) {

		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
			return fileName.substring(i + 1);
		}
		return "";
	}

	public static String getRelativePath(String path, String basePath) {
		return getRelativePath(new File(path), new File(basePath));
	}

	public static String getRelativePath(File fileOrFolder, File baseFolder) {
		if (!baseFolder.isDirectory())
			baseFolder = baseFolder.getAbsoluteFile().getParentFile();

		return baseFolder.toURI().relativize(fileOrFolder.toURI()).getPath();
	}

	public static boolean deleteFile(File file) throws IOException {
		if (file != null) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();

				for (File f: files) {
					deleteFile(f);
				}
			}
			return Files.deleteIfExists(file.toPath());
		}
		return false;
	}

	public static void deleteFile(String filepath)  {
		try {
			if (!FileUtil.deleteFile(new File(filepath)))
				FileUtil.deleteQuietly(new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
			Log.LOGGER.debug("Unable to delete the file: " + filepath);
		}
	}

	static boolean isEmptyFile(String source) {
		try {
			for (String line : Files.readAllLines(Paths.get(source))) {
				if (line != null && !line.trim().isEmpty()) {
					return false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Default to true.
		return true;
	}
}
