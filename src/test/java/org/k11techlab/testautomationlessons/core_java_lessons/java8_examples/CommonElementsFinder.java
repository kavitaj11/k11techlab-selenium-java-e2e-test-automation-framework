package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonElementsFinder {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> list2 = new ArrayList<>(List.of(4, 5, 6, 7, 8));

        Set<Integer> commonElements = findCommonElements(list1, list2);

        System.out.println("Common elements between the two lists: " + commonElements);
    }

    public static <T> Set<T> findCommonElements(List<T> list1, List<T> list2) {
        // Convert lists to sets to remove duplicates
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);

        // Retain only the elements that are present in both sets
        set1.retainAll(set2);

        return set1;
    }
}
