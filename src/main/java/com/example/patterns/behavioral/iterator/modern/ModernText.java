package com.example.patterns.behavioral.iterator.modern;

import java.util.stream.Stream;


public interface ModernText {
    Stream<String> linesStream();
}
