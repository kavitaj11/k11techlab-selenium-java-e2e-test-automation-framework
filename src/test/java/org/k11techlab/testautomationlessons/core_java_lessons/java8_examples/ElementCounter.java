package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;

public class ElementCounter {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);

        long count = countElements(numbers);

        System.out.println("Total number of elements in the list: " + count);
    }

    public static long countElements(List<Integer> numbers) {
        return numbers.stream().count();
    }
}
