package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;

public class AnagramChecker {
    public static void main(String[] args) {
        String str1 = "listen";
        String str2 = "silent";

        boolean areAnagrams = areAnagrams(str1, str2);

        if (areAnagrams) {
            System.out.println("Strings '" + str1 + "' and '" + str2 + "' are anagrams.");
        } else {
            System.out.println("Strings '" + str1 + "' and '" + str2 + "' are not anagrams.");
        }
    }

    public static boolean areAnagrams(String str1, String str2) {
        // Convert strings to lowercase to ignore case sensitivity
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // Sort the characters of both strings
        String sortedStr1 = sortString(str1);
        String sortedStr2 = sortString(str2);

        // Compare the sorted strings
        return sortedStr1.equals(sortedStr2);
    }

    private static String sortString(String str) {
        // Convert string to char array, sort the array, and then convert it back to string
        return Arrays.stream(str.split(""))
                .sorted()
                .reduce("", (s1, s2) -> s1 + s2);
    }
}
