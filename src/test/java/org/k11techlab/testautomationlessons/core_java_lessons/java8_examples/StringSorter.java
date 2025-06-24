package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;
import java.util.stream.Collectors;

public class StringSorter {
    public static List<String> sortStringsAlphabetically(List<String> strings) {
        return strings.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> strings = List.of("banana", "apple", "cherry");
        List<String> sortedStrings = sortStringsAlphabetically(strings);
        System.out.println("Sorted strings: " + sortedStrings);
    }
}
