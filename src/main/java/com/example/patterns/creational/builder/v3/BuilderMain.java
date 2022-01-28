package com.example.patterns.creational.builder.v3;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BuilderMain {
    public static void main(String[] args) {
        Director director = new Director();

        director.setBuilder(new EnterpriseWebsiteBuilder());
        Website website1 = director.buildWebsite();
        log.info(website1);

        director.setBuilder(new VisitCardWebsiteBuilder());
        Website website2 = director.buildWebsite();
        log.info(website2);
    }
}

@Setter
@ToString
class Website {
    private String name;
    private Cms cms;
    private int price;
}

@Setter
class Director {
    WebsiteBuilder builder;

    Website buildWebsite() {
        return builder
                .builder()
                .buildName()
                .buildCms()
                .buildPrice()
                .build();
    }
}

@Accessors(chain = true)
@Setter
@Getter
abstract class WebsiteBuilder {
    Website website;

    WebsiteBuilder builder() {
        final Website website = new Website();
        setWebsite(website);
        return this;
    }

    abstract WebsiteBuilder buildName();

    abstract WebsiteBuilder buildCms();

    abstract WebsiteBuilder buildPrice();

    Website build() {
        return getWebsite();
    }
}

class EnterpriseWebsiteBuilder extends WebsiteBuilder {
    @Override
    WebsiteBuilder buildName() {
        getWebsite().setName("Enterprise web site");
        return this;
    }

    @Override
    WebsiteBuilder buildCms() {
        build().setCms(Cms.BITRIX);
        return this;
    }

    @Override
    WebsiteBuilder buildPrice() {
        build().setPrice(10000);
        return this;
    }
}

class VisitCardWebsiteBuilder extends WebsiteBuilder {
    @Override
    WebsiteBuilder buildName() {
        build().setName("Visit card");
        return this;
    }

    @Override
    WebsiteBuilder buildCms() {
        build().setCms(Cms.WORDPRESS);
        return this;
    }

    @Override
    WebsiteBuilder buildPrice() {
        build().setPrice(500);
        return this;
    }
}

enum Cms {
    WORDPRESS, BITRIX;
}