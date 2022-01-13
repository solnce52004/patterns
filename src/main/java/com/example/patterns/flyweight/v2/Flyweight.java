package com.example.patterns.flyweight.v2;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Flyweight {
    static int CANVAS_SIZE = 500;
    static int TREES_TO_DRAW = 1000000;
    static int TREE_TYPES = 2;

    public static void main(String[] args) {
        Forest forest = new Forest();
        for (int i = 0; i < Math.floor(TREES_TO_DRAW / TREE_TYPES); i++) {
            forest.plantTree(
                    random(0, CANVAS_SIZE),
                    random(0, CANVAS_SIZE),
                    "Summer Oak",
                    Color.GREEN
            );
            forest.plantTree(
                    random(0, CANVAS_SIZE),
                    random(0, CANVAS_SIZE),
                    "Autumn Oak",
                    Color.ORANGE
            );
        }
        forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
        forest.setVisible(true);
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}

class Forest extends JFrame {
    private final ArrayList<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color) {
        TreeType type = TypeFactory.create(name, color);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    @Override
    public void paint(Graphics graphics) {
        for (Tree tree : trees) {
            tree.draw(graphics);
        }
    }
}

@Log4j2
class TypeFactory {
    static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType create(String name, Color color) {
        TreeType type = treeTypes.get(name);
        if (type == null) {
            type = new TreeType(name, color);
            log.info(type);//смотрим на ссылки реально созданный объектов
            treeTypes.put(name, type);
        }
        return type;
    }
}

@AllArgsConstructor
class Tree {
    private final int x;
    private final int y;
    private final TreeType type;

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }
}

@AllArgsConstructor
class TreeType {
    private final String name;
    private final Color color;

    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 1, y, 3, 5);
        g.setColor(color);
        g.fillOval(x - 5, y - 10, 10, 10);
    }
}
