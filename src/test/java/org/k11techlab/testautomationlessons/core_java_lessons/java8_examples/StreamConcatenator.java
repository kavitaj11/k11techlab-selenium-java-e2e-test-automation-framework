package org.k11techlab.testautomationlessons.core_java_lessons.java8_examples;

import java.util.stream.Stream;

public class StreamConcatenator {
    public static void main(String[] args) {
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        Stream<Integer> stream2 = Stream.of(4, 5, 6);

        Stream<Integer> concatenatedStream = concatenateStreams(stream1, stream2);

        concatenatedStream.forEach(System.out::print);
    }

    public static <T> Stream<T> concatenateStreams(Stream<T> stream1, Stream<T> stream2) {
        return Stream.concat(stream1, stream2);
    }
}
