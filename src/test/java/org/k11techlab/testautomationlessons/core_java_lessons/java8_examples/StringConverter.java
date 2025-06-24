package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;
import java.util.stream.Collectors;

public class StringConverter {
    public static List<String> convertToUpperCase(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> strings = List.of("apple", "banana", "cherry");
        List<String> uppercaseStrings = convertToUpperCase(strings);
        System.out.println("Uppercase strings: " + uppercaseStrings);
    }
}
