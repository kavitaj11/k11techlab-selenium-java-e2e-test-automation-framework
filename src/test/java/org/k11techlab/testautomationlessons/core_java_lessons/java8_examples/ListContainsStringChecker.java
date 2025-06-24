package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;

public class ListContainsStringChecker {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "orange", "grape");

        String searchString = "orange";

        boolean containsString = containsString(list, searchString);

        if (containsString) {
            System.out.println("List contains the string: " + searchString);
        } else {
            System.out.println("List does not contain the string: " + searchString);
        }
    }

    public static boolean containsString(List<String> list, String searchString) {
        return list.stream()
                .anyMatch(searchString::equals); // Using method reference
    }
}