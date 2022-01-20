package com.example.patterns.behavioral.templatemethod.v1;

public class WelcomePage extends WebsiteTemplate {

    @Override
    public void showPageContent() {
        System.out.println("Welcome");
    }
}
