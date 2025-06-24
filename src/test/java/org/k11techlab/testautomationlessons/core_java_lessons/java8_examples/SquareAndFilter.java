package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SquareAndFilter {

        public static void main(String[] args) {
            List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50, 110, 120);

            List<Integer> squaredAndFilteredNumbers = squareAndFilterNumbers(numbers);

            System.out.println("Numbers squared and filtered: " + squaredAndFilteredNumbers);
        }

        public static List<Integer> squareAndFilterNumbers(List<Integer> numbers) {
            return numbers.stream()
                    .map(n -> n * n) // Square each element
                    .filter(n -> n > 1000) // Filter numbers greater than 1000
                    .collect(Collectors.toList());
        }
}
