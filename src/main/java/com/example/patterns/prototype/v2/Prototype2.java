package com.example.patterns.prototype.v2;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Prototype2 {
    public static void main(String[] args) {
        Editor editor = new Editor();
        Circle circle = editor.addCircle();
        Rectangle rectangle = editor.addRectangle();

        log.info(circle.getSquare());
        circle.changeRadix(5);
        log.info(circle.getSquare());

        log.info(rectangle.getSquare());
        rectangle.changeSize(1, -1);
        log.info(rectangle.getSquare());

        log.info(editor.getFigures());
    }
}

interface Figure {
    double getSquare();
    Figure clone();
}

@Getter
@NoArgsConstructor
class Editor {
    private final List<Figure> figures = new ArrayList<>();
    private final Circle defaultCircle = new Circle(5);
    private final Rectangle defaultRectangle = new Rectangle(2, 4);

    public Circle addCircle() {
        Circle circle = defaultCircle.clone();
        figures.add(circle);
        return circle;
    }

    public Rectangle addRectangle() {
        Rectangle rectangle = defaultRectangle.clone();
        figures.add(rectangle);
        return rectangle;
    }
}

@Getter
@ToString @EqualsAndHashCode
@AllArgsConstructor
class Circle implements Figure {
    private int radix;

    public void changeRadix(int delta) {
        radix += delta;
    }

    @Override
    public double getSquare() {
        return Math.PI * radix * radix;
    }

    @Override
    public Circle clone() {
        return new Circle(radix);
    }
}

@Getter
@ToString @EqualsAndHashCode
@AllArgsConstructor
class Rectangle implements Figure {
    private int width;
    private int height;

    public void changeSize(int widthDelta, int heightDelta) {
        width += widthDelta;
        height += heightDelta;
    }

    @Override
    public double getSquare() {
        return width * height;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(width, height);
    }
}




