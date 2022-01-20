package com.example.patterns.behavioral.templatemethod.v1;

public class WebsiteRunner {
    public static void main(String[] args) {
        WebsiteTemplate welcomePage = new WelcomePage();
        WebsiteTemplate newsPage = new NewsPage();

        welcomePage.showPage();

        System.out.println("\n================================\n");

        newsPage.showPage();
    }
}
