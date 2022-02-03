package com.example.patterns.behavioral.templatemethod.v2;

import lombok.extern.log4j.Log4j2;

public class TemplateMain {
    public static void main(String[] args) {
        LayoutTemplate welcomePage = new WelcomePage();
        LayoutTemplate newsPage = new NewsPage();

        welcomePage.showPage();
        newsPage.showPage();
    }
}
@Log4j2
abstract class LayoutTemplate {
    public final void showPage() { //не мб переопределен в наследниках!!!
        log.info("Header");
        showPageContent();
        log.info("Footer");
    }

    public abstract void showPageContent();
}

@Log4j2
class NewsPage extends LayoutTemplate {
    @Override
    public void showPageContent() {
        log.info("News");
    }
}

@Log4j2
class WelcomePage extends LayoutTemplate {

    @Override
    public void showPageContent() {
        log.info("Welcome");
    }
}
