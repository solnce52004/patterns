package com.example.patterns.behavioral.templatemethod.v1;

public class NewsPage extends WebsiteTemplate {
    @Override
    public void showPageContent() {
        System.out.println("News");
    }
}
