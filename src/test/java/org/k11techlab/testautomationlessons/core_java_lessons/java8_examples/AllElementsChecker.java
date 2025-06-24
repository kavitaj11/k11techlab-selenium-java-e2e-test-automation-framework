package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;

public class AllElementsChecker {
    public static boolean areAllElementsGreaterThan(List<Integer> numbers, int threshold) {
        return numbers.stream()
                .allMatch(n -> n > threshold);
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 20, 30, 40, 50);
        int threshold = 25;
        boolean result = areAllElementsGreaterThan(numbers, threshold);
        System.out.println("Are all elements greater than " + threshold + "? " + result);
    }
}