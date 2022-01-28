package com.example.patterns.structural.bridge.v3;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

public class BridgeMain {
    public static void main(String[] args) {
        Program[] programs = {
                new BankSystem(new CppDeveloper()),
                new StockExchange(new JavaDeveloper())
        };

        for (Program program : programs) {
            program.developProgram();
        }
    }
}

//в будущем расширять с помощью подклассов и реализацию и абстракцию
@AllArgsConstructor
abstract class Program {
    protected Developer developer;
    public abstract void developProgram();
}

@Log4j2
class BankSystem extends Program {
    protected BankSystem(Developer developer) {
        super(developer);
    }

    @Override
    public void developProgram() {
        log.info("Bank System development in progress...");
        developer.writeCode();
    }
}

@Log4j2
class StockExchange extends Program {
    protected StockExchange(Developer developer) {
        super(developer);
    }

    @Override
    public void developProgram() {
        log.info("Stock Exchange development in progress...");
        developer.writeCode();
    }
}

interface Developer {
    void writeCode();
}

@Log4j2
class CppDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("C++ developer writes C++ code...");
    }
}

@Log4j2
class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("Java developer writes Java code...");
    }
}
