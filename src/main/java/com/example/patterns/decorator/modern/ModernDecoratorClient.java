package com.example.patterns.decorator.modern;

import com.example.patterns.decorator.canonical.DigitCounter;
import com.example.patterns.decorator.canonical.NaiveDigitCounter;
import lombok.extern.log4j.Log4j2;

import java.util.function.Function;

@Log4j2
public class ModernDecoratorClient {
    public static void main(String[] args) {
        DigitCounter counter = wrap(new NaiveDigitCounter());
        int digitsCount = counter.count("fd6j78fh19kj");
        log.info(digitsCount + " digits found");

        digitsCount = ((Function<String, String>) String::trim)
                .andThen(new NaiveDigitCounter()::count)
                .apply(" \n 123 \t");
        log.info(digitsCount + " digits found");
    }

    public static DigitCounter wrap(DigitCounter counter) {
        return s -> {
            long startTime = System.currentTimeMillis();
            int count = counter.count(s);
            long endTime = System.currentTimeMillis();
            log.info("Counting took " + (endTime - startTime) + " ms");
            return count;
        };
    }
}
