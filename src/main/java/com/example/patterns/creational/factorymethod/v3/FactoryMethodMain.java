package com.example.patterns.creational.factorymethod.v3;

import lombok.extern.log4j.Log4j2;

public class FactoryMethodMain {
    public static void main(String[] args) {
        final DeveloperFactory factoryJava = Configurer.configure(Lang.JAVA);
        Developer developerJava = factoryJava.createDeveloper();
        developerJava.writeCode();

        final DeveloperFactory factoryCpp = Configurer.configure(Lang.CPP);
        Developer developerCpp = factoryCpp.createDeveloper();
        developerCpp.writeCode();
    }
}

class Configurer {
    public static DeveloperFactory configure(Lang lang) {
        switch (lang) {
            case JAVA:
                return new JavaDeveloperFactory();
            case CPP:
            default:
                return new CppDeveloperFactory();
        }
    }
}

@Log4j2
abstract class DeveloperFactory {
    protected void logging(){
        log.info("creating new developer...");
    }

    abstract Developer createDeveloper();//фабричный метод
}

class CppDeveloperFactory extends DeveloperFactory {
    @Override
    public Developer createDeveloper() {
        logging();
        return new CppDeveloper();
    }
}

class JavaDeveloperFactory extends DeveloperFactory {
    @Override
    public Developer createDeveloper() {
        logging();
        return new JavaDeveloper();
    }
}

interface Developer {
    void writeCode();
}

@Log4j2
class CppDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("C++ developer");
    }
}

@Log4j2
class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("Java developer");
    }
}

enum Lang {
    JAVA, CPP
}
