package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntegersSorter {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);

        List<Integer> sortedNumbers = sortIntegers(numbers);

        System.out.println("Sorted numbers: " + sortedNumbers);


        List<Integer> numbers2 = Arrays.asList(5, 2, 8, 1, 9, 3);

        List<Integer> sortedNumbers2 = sortIntegersDescending(numbers2);

        System.out.println("Sorted numbers in descending order: " + sortedNumbers2);

    }

    public static List<Integer> sortIntegers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Integer> sortIntegersDescending(List<Integer> numbers) {
        return numbers.stream()
                .sorted(java.util.Comparator.reverseOrder()) // Sort in descending order
                .collect(Collectors.toList());
    }
}

