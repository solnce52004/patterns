package com.example.patterns.prototype.canonical;


public class PrototypeClient {
    public static void main(String[] args) {
        Editor editor = new Editor();

        Circle circle = editor.addCircle();
        circle.changeRadix(5);

        Rectangle rectangle = editor.addRectangle();
        rectangle.changeSize(1, -1);
    }
}
