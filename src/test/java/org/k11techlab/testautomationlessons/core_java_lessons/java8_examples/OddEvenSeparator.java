package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OddEvenSeparator {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean, List<Integer>> oddEvenMap = separateOddEven(numbers);

        System.out.println("Odd numbers: " + oddEvenMap.get(false));
        System.out.println("Even numbers: " + oddEvenMap.get(true));
    }

    public static Map<Boolean, List<Integer>> separateOddEven(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
    }
}
