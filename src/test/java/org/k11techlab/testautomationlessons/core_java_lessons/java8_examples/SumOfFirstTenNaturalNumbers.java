package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.stream.IntStream;

public class SumOfFirstTenNaturalNumbers {
    public static void main(String[] args) {
        int sum = sumOfFirstNNaturalNumbers(10);
        System.out.println("Sum of the first 10 natural numbers: " + sum);
    }

    public static int sumOfFirstNNaturalNumbers(int n) {
        return IntStream.rangeClosed(1, n)
                .sum();
    }
}
