package com.example.patterns.visitor.canonical;


public class AreaVisitor implements Visitor<Double> {
    @Override
    public Double visit(Square element) {
        return element.getSide() * element.getSide();
    }

    @Override
    public Double visit(Circle element) {
        return Math.PI * element.getRadius() * element.getRadius();
    }

    @Override
    public Double visit(Rectangle element) {
        return element.getHeight() * element.getWidth();
    }
}
