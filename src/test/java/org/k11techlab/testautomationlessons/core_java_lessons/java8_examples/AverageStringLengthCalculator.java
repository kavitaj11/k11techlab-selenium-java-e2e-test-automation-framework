package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;

public class AverageStringLengthCalculator {
    public static double calculateAverageStringLength(List<String> strings) {
        return strings.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);
    }

    public static void main(String[] args) {
        List<String> strings = List.of("apple", "banana", "orange", "grape", "kiwi");
        double averageLength = calculateAverageStringLength(strings);
        System.out.println("Average length of strings: " + averageLength);
    }
}
