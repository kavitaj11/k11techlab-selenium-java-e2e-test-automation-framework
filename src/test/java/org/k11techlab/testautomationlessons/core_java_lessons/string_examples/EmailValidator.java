package org.k11techlab.testautomationlessons.core_java_lessons.string_examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    // Regular Expression for validating email addresses
    private static final String EMAIL_REGEX = 
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isValidEmail(String email) {
        // Compile the regex pattern
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        // Match the input email with the pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        // Test cases
        String[] testEmails = {
                "test.email+alex@domain.com",     // Valid
                "user@sub.domain.com",           // Valid (subdomain)
                "user@domain.co.uk",             // Valid (country code TLD)
                "user.name@domain.com",          // Valid (dot in local part)
                "user_name@domain.com",          // Valid (underscore in local part)
                "user-name@domain.com",          // Valid (dash in local part)
                "user@domain.travel",            // Valid (custom TLD)
                "user@localhost",                // Valid (localhost domain)
                "user@192.168.1.1",              // Valid (IP as domain)
                "invalid-email@",                // Invalid (no domain part)
                "@domain.com",                   // Invalid (no local part)
                "user@domain",                   // Invalid (no TLD)
                "user@domain..com",              // Invalid (double dots in domain)
                "user@.com",                     // Invalid (dot starts the domain)
                "user@domain.c",                 // Invalid (single-character TLD)
                "user@-domain.com",              // Invalid (dash starts domain)
                "user@domain-.com",              // Invalid (dash ends domain)
                "user@domain.com-",              // Invalid (dash after TLD)
                "user@@domain.com",              // Invalid (double @ symbol)
                "user@domain..com",              // Invalid (double dots in domain)
        };

        // Validate each email address and print the result
        for (String email : testEmails) {
            System.out.printf("Email: %-30s | Valid: %s%n", email, isValidEmail(email));
        }
    }
}
