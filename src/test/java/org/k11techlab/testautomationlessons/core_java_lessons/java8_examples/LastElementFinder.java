package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LastElementFinder {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> lastElement = findLastElement(numbers);
        if (lastElement.isPresent()) {
            System.out.println("Last element: " + lastElement.get());
        } else {
            System.out.println("List is empty");
        }
    }

    public static <T> Optional<T> findLastElement(List<T> list) {
        return list.stream()
                .reduce((first, second) -> second);
    }
}
