package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyCounter {
    public static Map<String, Long> countWordFrequency(List<String> words) {
        return words.stream()
                .flatMap(line -> List.of(line.split("\\s+")).stream())
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
    }

    public static void main(String[] args) {
        List<String> words = List.of("apple banana apple", "banana cherry", "apple banana cherry");
        Map<String, Long> frequencyMap = countWordFrequency(words);
        System.out.println("Word frequency: " + frequencyMap);
    }
}
