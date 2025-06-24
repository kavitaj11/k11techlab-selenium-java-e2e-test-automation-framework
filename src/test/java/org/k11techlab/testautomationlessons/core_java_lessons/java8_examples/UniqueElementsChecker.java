package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;
import java.util.stream.Collectors;

public class UniqueElementsChecker {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        boolean allUnique = areAllElementsUnique(numbers);
        if (allUnique) {
            System.out.println("All elements in the list are unique.");
        } else {
            System.out.println("List contains duplicate elements.");
        }
    }

    public static boolean areAllElementsUnique(List<Integer> list) {
        return list.stream()
                .collect(Collectors.toSet())
                .size() == list.size();
    }
}

