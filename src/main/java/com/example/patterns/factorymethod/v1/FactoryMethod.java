package com.example.patterns.factorymethod.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.function.BiFunction;

@Log4j2
public class FactoryMethod {
    public static void main(String[] args) {
        final Doc json = SpecialDocFactory.create("json");
        final Doc xlsx = SpecialDocFactory.create("xlsx");
        log.info(json);
        log.info(xlsx);

        final BiFunction<String, String, JsonDoc> plainFactory = JsonDoc::new;
        log.info(plainFactory.apply("json", "j2"));
    }
}

class SpecialDocFactory {
    public static Doc create(String ext) {
        if ("xlsx".equals(ext)) {
            return new XlsxDoc().setExt(ext);
        }
        return new JsonDoc().setExt(ext);
    }
}

interface Doc {
    String getExt();
}

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class JsonDoc implements Doc {
    private String ext;
    private String name = "JsonDoc";
}

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class XlsxDoc implements Doc {
    private String ext;
    private String page = "page_1";
}
