package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.List;

public class CharacterCounter {
    public static long countCharacterOccurrences(List<String> strings, char targetChar) {
        return strings.stream()
                .flatMapToInt(CharSequence::chars)
                .filter(ch -> ch == targetChar)
                .count();
    }

    public static void main(String[] args) {
        List<String> strings = List.of("apple", "banana", "cherry");
        char targetChar = 'a';
        long occurrences = countCharacterOccurrences(strings, targetChar);
        System.out.println("Occurrences of '" + targetChar + "': " + occurrences);
    }
}
