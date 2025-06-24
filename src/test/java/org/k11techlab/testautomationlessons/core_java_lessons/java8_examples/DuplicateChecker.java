package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;

public class DuplicateChecker {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println("Contains duplicates: " + containsDuplicates(nums));
    }

    public static boolean containsDuplicates(int[] nums) {
        return Arrays.stream(nums)
                .boxed() // Convert int[] to Stream<Integer>
                .distinct() // Remove duplicates
                .count() != nums.length; // Check if count is different from array length
    }
}

