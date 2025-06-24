package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;

public class MultiplesOfThreePrinter {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 6, 9, 20, 25, 40);
        printMultiplesOfThree(numbers);
    }

    public static void printMultiplesOfThree(List<Integer> numbers) {
        System.out.println("Multiples of 3:");
        numbers.stream()
                .filter(n -> n % 3 == 0) // Filter numbers that are multiples of 3
                .forEach(System.out::println); // Print each multiple of 3
    }
}
