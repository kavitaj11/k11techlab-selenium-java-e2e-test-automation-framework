package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;

public class DigitSumCalculator {
    public static int calculateDigitSum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(n -> String.valueOf(n).chars().map(Character::getNumericValue).sum())
                .sum();
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(123, 456, 789);
        int digitSum = calculateDigitSum(numbers);
        System.out.println("Sum of digits: " + digitSum);
    }
}
