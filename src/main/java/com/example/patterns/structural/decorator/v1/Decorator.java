package com.example.patterns.structural.decorator.v1;

import lombok.extern.log4j.Log4j2;

import java.util.function.Function;

@Log4j2
public class Decorator {
    public static void main(String[] args) {
        Counter counter = BenchmarkWrapper.wrap(new NaiveCounter());
        int benchmarkDigits = counter.countDigit("fd6j78fh19kj".trim());
        log.info(benchmarkDigits + " benchmark digits");

        int naiveDigits = ((Function<String, String>) String::trim)
                .andThen(new NaiveCounter()::countDigit)
                .apply(" \n 123 \t");
        log.info(naiveDigits + " naive digits");
    }
}

@Log4j2
class BenchmarkWrapper {
    public static Counter wrap(Counter counter) {
        return s -> {
            long start = System.currentTimeMillis();
            int count = counter.countDigit(s);
            long end = System.currentTimeMillis();
            log.info("Counting took " + (end - start) + " ms");
            return count;
        };
    }
}

class NaiveCounter implements Counter {
    @Override
    public int countDigit(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }
}

interface Counter {
    int countDigit(String str);
}