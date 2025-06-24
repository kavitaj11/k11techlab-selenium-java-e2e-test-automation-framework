package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateElementExtractor {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 2, 3, 5, 6, 7, 8, 9, 1};

        List<Integer> duplicates = findDuplicateElements(array);

        System.out.println("Duplicate elements: " + duplicates);
    }

    public static List<Integer> findDuplicateElements(Integer[] array) {
        Map<Integer, Long> countMap = Arrays.stream(array)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        return countMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
