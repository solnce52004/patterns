package com.example.patterns.iterator;

import java.util.stream.Stream;


public interface ModernText {
    Stream<String> linesStream();
}
