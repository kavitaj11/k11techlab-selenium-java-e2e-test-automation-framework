package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.stream.IntStream;

public class FirstTenOddNumbers {
    public static void main(String[] args) {
        System.out.println("First 10 odd numbers:");
        printFirstTenOddNumbers();
    }

    public static void printFirstTenOddNumbers() {
        IntStream.iterate(1, n -> n + 2) // Start from 1 and increment by 2 to get odd numbers
                .limit(5) // Limit to the first 5 odd numbers
                .forEach(System.out::println); // Print each odd number
    }
}
