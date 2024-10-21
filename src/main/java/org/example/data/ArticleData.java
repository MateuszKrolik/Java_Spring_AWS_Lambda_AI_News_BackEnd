package org.example.data;

import org.example.dtos.NewsArticle;

import java.util.ArrayList;
import java.util.List;

public class ArticleData {
    public static List<NewsArticle> initializeArticles() {
        List<NewsArticle> allArticles = new ArrayList<>();

        // Local articles
        allArticles.add(new NewsArticle("Local News in New York", "This is a local news article about New York.", "New York"));
        allArticles.add(new NewsArticle("Local News in Los Angeles", "This is a local news article about Los Angeles.", "Los Angeles"));
        allArticles.add(new NewsArticle("Local News in Florida", "This is a local news article about Florida.", "Florida"));
        allArticles.add(new NewsArticle("Local News in Arizona", "This is a local news article about Arizona.", "Arizona"));

        // Global articles
        allArticles.add(new NewsArticle("Global News Event", "This is a global news article about an event happening worldwide.", null));

        return allArticles;
    }
}