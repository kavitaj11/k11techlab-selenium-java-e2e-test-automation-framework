package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class StringLengthSorter {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("banana", "apple", "orange", "grape", "kiwi");

        List<String> sortedStrings = sortStringsByLength(strings);

        System.out.println("Sorted strings by length (decreasing order): " + sortedStrings);
    }

    public static List<String> sortStringsByLength(List<String> strings) {
        return strings.stream()
                .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length())) // Sort strings by length in decreasing order
                .collect(Collectors.toList()); // Convert the stream to a list
    }
}
