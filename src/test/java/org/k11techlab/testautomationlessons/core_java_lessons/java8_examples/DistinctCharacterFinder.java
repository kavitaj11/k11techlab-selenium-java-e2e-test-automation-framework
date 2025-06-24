package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DistinctCharacterFinder {
    public static Set<Character> findDistinctCharacters(List<String> strings) {
        return strings.stream()
                .flatMapToInt(String::chars)
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        List<String> strings = List.of("apple", "banana", "cherry");
        Set<Character> distinctChars = findDistinctCharacters(strings);
        System.out.println("Distinct characters: " + distinctChars);
    }
}
