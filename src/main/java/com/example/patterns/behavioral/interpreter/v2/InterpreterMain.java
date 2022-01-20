package com.example.patterns.behavioral.interpreter.v2;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InterpreterMain {
    public static void main(String[] args) {
        Expression isJava = getJavaExpression();
        Expression isJavaEEDeveloper = getJavaEEExpression();

        log.info("Does developer knows Java Core: {}", isJava.interpret("Java Core"));
        log.info("Does developer knows Java EE: {}", isJavaEEDeveloper.interpret("Java Spring"));
    }

    public static Expression getJavaExpression() {
        Expression java = new TerminalExpression("Java");
        Expression javaCore = new TerminalExpression("Java Core");
        return new OrExpression(java, javaCore);
    }

    public static Expression getJavaEEExpression() {
        Expression java = new TerminalExpression("Java");
        Expression spring = new TerminalExpression("Spring");
        return new AndExpression(java, spring);
    }
}

interface Expression {
    boolean interpret(String context);
}

@AllArgsConstructor
class TerminalExpression implements Expression {
    private final String data;

    @Override
    public boolean interpret(String context) {
        return context.contains(data);//конечный метод (терминальная операция)
    }
}

@AllArgsConstructor
class AndExpression implements Expression {
    private final Expression expression1;
    private final Expression expression2;

    @Override
    public boolean interpret(String context) {
        return expression1.interpret(context) && expression2.interpret(context);
    }
}

@AllArgsConstructor
class OrExpression implements Expression {
    private final Expression expression1;
    private final Expression expression2;

    @Override
    public boolean interpret(String context) {
        return expression1.interpret(context) || expression2.interpret(context);
    }
}





