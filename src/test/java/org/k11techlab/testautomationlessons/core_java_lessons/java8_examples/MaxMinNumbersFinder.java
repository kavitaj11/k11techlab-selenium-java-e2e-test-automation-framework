package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaxMinNumbersFinder {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 20, 15, 25, 3, 30, 1, 8);

        List<Integer> maxNumbers = findMaxNumbers(numbers, 3);
        List<Integer> minNumbers = findMinNumbers(numbers, 3);

        System.out.println("Three maximum numbers: " + maxNumbers);
        System.out.println("Three minimum numbers: " + minNumbers);
    }

    public static List<Integer> findMaxNumbers(List<Integer> numbers, int count) {
        return numbers.stream()
                .sorted((a, b) -> Integer.compare(b, a)) // Sort in descending order
                .limit(count) // Limit to count of maximum numbers
                .collect(Collectors.toList());
    }

    public static List<Integer> findMinNumbers(List<Integer> numbers, int count) {
        return numbers.stream()
                .sorted() // Sort in ascending order (default)
                .limit(count) // Limit to count of minimum numbers
                .collect(Collectors.toList());
    }
}
