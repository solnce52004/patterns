package com.example.patterns.factory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.function.Function;

@Log4j2
public class AbstractFactory {
    public static void main(String[] args) {
        boolean someCondition = true;
        App app;
        DocFactory factory;

        if (someCondition) {
          factory = JsonDocFactory.getInstance();
            app = new App(factory, "json_doc_1");
        } else {
            factory = XlsxDocFactory.getInstance();
            app = new App(factory, "xlsx_doc_1");
        }
        app.printDocInfo();

        Function<String, Doc> plainFactory = JsonDoc::new;
        log.info(plainFactory.apply("json_doc_2"));
    }
}

@Log4j2
class App {
    private final Doc document;
    private final Description description;

    public App(DocFactory factory, String name) {
        document = factory.createDocument(name);
        description = factory.createDescription();
    }

    public void printDocInfo() {
        log.info(document.getName());
        log.info(description.getDescription());
    }
}

interface Doc {
    String getName();
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class JsonDoc implements Doc {
    private String name;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class XlsxDoc implements Doc {
    private String name;
}

interface Description {
    String getDescription();
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class JsonDescription implements Description {
    private String description = "JsonDescription";
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class XlsxDescription implements Description {
    private String description = "JsonDescription";
}

//фабрика документов и описания к ним
interface DocFactory {
    Doc createDocument(String name);

    Description createDescription();
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class JsonDocFactory implements DocFactory {
    private static volatile JsonDocFactory instance;

    public static synchronized JsonDocFactory getInstance() {
        JsonDocFactory result = instance;
        if (result != null) {
            return result;
        }
        if (instance == null) {
            synchronized (JsonDocFactory.class) {
                instance = new JsonDocFactory();
            }
        }
        return instance;
    }

    @Override
    public Doc createDocument(String name) {
        return new JsonDoc(name);
    }

    @Override
    public Description createDescription() {
        return new JsonDescription();
    }
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class XlsxDocFactory implements DocFactory {
    private static volatile XlsxDocFactory instance;

    //Double-Checked Locking
    public static synchronized XlsxDocFactory getInstance() {
        XlsxDocFactory result = instance;
        if (result != null) {
            return result;
        }
        if (instance == null) {
            synchronized (XlsxDocFactory.class) {
                instance = new XlsxDocFactory();
            }
        }
        return instance;
    }

    @Override
    public Doc createDocument(String name) {
        return new XlsxDoc(name);
    }

    @Override
    public Description createDescription() {
        return new XlsxDescription();
    }
}
