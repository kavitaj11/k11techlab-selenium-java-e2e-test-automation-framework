package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;
import java.util.stream.Collectors;

public class IntegerListConverter {
    public static String convertToCommaSeparatedString(List<Integer> numbers) {
        return numbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        String commaSeparatedString = convertToCommaSeparatedString(numbers);
        System.out.println("Comma-separated string: " + commaSeparatedString);
    }
}
