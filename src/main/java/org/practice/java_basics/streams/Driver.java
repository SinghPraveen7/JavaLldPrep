package org.practice.java_basics.streams;

import java.util.*;
import java.util.stream.Collectors;

public class Driver {

    public static void main(String[] args) {
        // java8-Collections-GroupWordsByFirstLetter
        String sentence = "apple amazon ball bat cat car dog learning";
        Map<Character, List<String>> map = Arrays.stream(sentence.split("\\s+"))
                .collect(Collectors.groupingBy(word -> word.charAt(0)));
        System.out.println(map);

        // java8-Find the second highest length in a given sentence of words
        Optional<Integer> result = Arrays.stream(sentence.split("\\s+"))
                .map(String::length)
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println(result.orElse(-1));

        // java8-Filter out only the valid integers from a list of strings
        List<String> input = Arrays.asList("123", "-456", "abc", "78.9", "0", "+42", null);
        List<String> output = input.stream()
                .filter(Objects::nonNull)
                .filter(s -> {
                    try {
                        Integer.parseInt(s);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
        System.out.println(output);

        // java8-Convert sentence to hashtag
        List<String> output2 = Arrays.stream(sentence.split("\\s+"))
                .map(s -> "#" + s)
                .collect(Collectors.toList());
        System.out.println(output2);

        // java8-Find duplicate elements
        List<Integer> arr = Arrays.asList(11, 12, 14, 15, 17, 14);
        Set<Integer> seen = new HashSet<>();
        Optional<Integer> duplicate = arr.stream()
                .filter(n -> {
                    return !seen.add(n);
                }).findFirst();
        System.out.println(duplicate.orElse(-1));
    }

}
