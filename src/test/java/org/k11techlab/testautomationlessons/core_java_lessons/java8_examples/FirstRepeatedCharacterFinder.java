package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.HashSet;

public class FirstRepeatedCharacterFinder {
    public static void main(String[] args) {
        String str = "hello world";

        char firstRepeatedChar = findFirstRepeatedCharacter(str);

        if (firstRepeatedChar != '\0') {
            System.out.println("First repeated character: " + firstRepeatedChar);
        } else {
            System.out.println("No repeated characters found");
        }
    }

    public static char findFirstRepeatedCharacter(String str) {
        HashSet<Character> set = new HashSet<>();

        return str.chars() // Convert the string to an IntStream of characters
                .mapToObj(ch -> (char) ch) // Convert each character code to its corresponding character
                .filter(ch -> !set.add(ch)) // Filter out characters that are already in the set (i.e., repeated characters)
                .findFirst() // Find the first repeated character
                .orElse('\0'); // Return '\0' if no repeated characters found
    }
}
