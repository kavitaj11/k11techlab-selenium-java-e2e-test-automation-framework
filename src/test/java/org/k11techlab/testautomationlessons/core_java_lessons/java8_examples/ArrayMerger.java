package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.IntStream;


public class ArrayMerger {
    public static void main(String[] args) {
        int[] arr1 = {3, 6, 8, 10, 10};
        int[] arr2 = {1, 2, 4, 5};

        int[] mergedArray1 = mergeAndSortArrays(arr1, arr2);

        System.out.println("Merged and sorted array: " + Arrays.toString(mergedArray1));

        int[] arr3 = {4, 2, 6, 1};
        int[] arr4 = {7, 3, 5, 8};

        int[] mergedArray2 = mergeAndSortArraysWithoutDuplicates(arr3, arr4);

        System.out.println("Merged and sorted array without duplicates: " + Arrays.toString(mergedArray2));

    }

    public static int[] mergeAndSortArrays(int[] arr1, int[] arr2) {
        return IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .sorted()
                .toArray();
    }


    public static int[] mergeAndSortArraysWithoutDuplicates(int[] arr1, int[] arr2) {
        return IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
// Concatenate both arrays into a single stream
                .distinct() // Remove duplicates
                .sorted() // Sort the elements of the stream
                .toArray(); // Convert the sorted stream to an array
    }
}
