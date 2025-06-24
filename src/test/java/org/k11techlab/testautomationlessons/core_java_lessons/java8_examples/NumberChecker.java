package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;

import java.util.Arrays;
import java.util.List;

public class NumberChecker {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        boolean containsThree = checkIfContainsNumber(numbers, 3);
        System.out.println("Contains 3: " + containsThree);
    }

    public static boolean checkIfContainsNumber(List<Integer> numbers, int targetNumber) {
        return numbers.stream()
                .anyMatch(n -> n == targetNumber);
    }
}
