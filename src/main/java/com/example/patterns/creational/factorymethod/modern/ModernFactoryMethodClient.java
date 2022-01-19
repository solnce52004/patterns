package com.example.patterns.creational.factorymethod.modern;

import com.example.patterns.creational.factorymethod.canonical.Document;
import com.example.patterns.creational.factorymethod.canonical.DocumentFactory;
import com.example.patterns.creational.factorymethod.canonical.JsonDocument;

import java.util.function.Function;


public class ModernFactoryMethodClient {
    public static void main(String[] args) {
        DocumentFactory factory = JsonDocument::new;
        printUserDetails(factory.create("USER"));

        Function<String, Document> plainFactory = JsonDocument::new;
        printUserDetails(plainFactory.apply("USER"));
    }

    private static void printUserDetails(Document document) {
        document.addField("name", "M");
        document.addField("surname", "M");
        System.out.println(document);
    }
}
