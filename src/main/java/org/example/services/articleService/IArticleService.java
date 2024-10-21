package org.example.services.articleService;

import org.example.dtos.ClassificationResult;
import org.example.dtos.NewsArticle;

import java.util.List;

public interface IArticleService {
    List<NewsArticle> fetchRelevantArticles(ClassificationResult classificationResult);
}