package com.example.patterns.iterator.modern;

import java.util.stream.Stream;


public interface ModernText {
    Stream<String> linesStream();
}
