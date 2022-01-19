package com.example.patterns.behavioral.visitor.canonical;


public interface Element {
    <T> T accept(Visitor<T> visitor);
}
