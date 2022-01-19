package com.example.patterns.creational.factorymethod.v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.function.BiFunction;

import static com.example.patterns.creational.factorymethod.v2.Ext.*;

@Log4j2
public class FactoryMethod2 {
    public static void main(String[] args) {
        Store configure = Configurer.configure(CSV);
        final SpecialDocument document = configure.createDocument();
        log.info(document);

        final BiFunction<Ext, String, WordDoc> plainFactory = WordDoc::new;
        log.info(plainFactory.apply(WORD, "word_name_2"));
    }
}

class Configurer {
    public static Store configure(Ext ext) {
        switch (ext) {
            case CSV:
            case XLSX:
                return new CsvStore();
            case WORD:
            default:
                return new WordStore();
        }
    }
}

// В классах-наследниках помимо унаследованной логики
// будет реализован фабричный метод
abstract class Store {
    public String store() {
        return "stored";
    }

    public abstract SpecialDocument createDocument();
}

class CsvStore extends Store {
    @Override
    public SpecialDocument createDocument() {
        return new CsvDoc();
    }
}

class WordStore extends Store {
    @Override
    public SpecialDocument createDocument() {
        return new WordDoc();
    }
}

interface SpecialDocument {
    Ext getExt();
}

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class WordDoc implements SpecialDocument {
    private Ext ext = WORD;
    private String name = "word_name";
}

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class CsvDoc implements SpecialDocument {
    private Ext ext = CSV;
    private String page = "csv_page_1";
}

enum Ext {
    WORD, CSV, XLSX
}
