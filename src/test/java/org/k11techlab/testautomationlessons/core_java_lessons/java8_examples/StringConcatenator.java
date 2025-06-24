package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;
import java.util.stream.Collectors;

public class StringConcatenator {
    public static String concatenateStrings(List<String> strings) {
        return strings.stream()
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        List<String> strings = List.of("Hello", " ", "World", "!");
        String concatenatedString = concatenateStrings(strings);
        System.out.println("Concatenated string: " + concatenatedString);
    }
}
