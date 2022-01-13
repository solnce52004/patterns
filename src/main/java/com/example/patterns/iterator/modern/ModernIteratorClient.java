package com.example.patterns.iterator.modern;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ModernIteratorClient {
    public static void main(String[] args) {
        ModernText text = new ModernMultilineText("This is just \n" +
                " a simple multiline \n" +
                "\n" +
                " text\n" +
                "\n", "\n");
        text.linesStream().forEach(System.out::println);

        String sentence = "JEEConf is the oldest Java conference in Ukraine";
        System.out.println(capitalize(sentence));
    }

    private static String capitalize(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .filter(word -> word.length() > 4)
                .map(String::toUpperCase)
                .collect(Collectors.joining(" "));
    }
}
