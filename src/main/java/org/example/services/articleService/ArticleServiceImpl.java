package org.example.services.articleService;

import org.example.data.ArticleData;
import org.example.dtos.ClassificationResult;
import org.example.dtos.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {
    private final List<NewsArticle> allArticles;

    public ArticleServiceImpl() {
        this.allArticles = ArticleData.initializeArticles();
    }

    @Override
    public List<NewsArticle> fetchRelevantArticles(ClassificationResult classificationResult) {
        String classification = classificationResult.getClassification();
        String location = classificationResult.getLocation();

        System.out.println("Filtering articles for classification: " + classification + ", location: " + location);

        List<NewsArticle> filteredArticles = new ArrayList<>();

        // Filtering logic
        for (NewsArticle article : allArticles) {
            if ("local".equalsIgnoreCase(classification) && article.getLocation() != null && article.getLocation().equalsIgnoreCase(location)) {
                filteredArticles.add(article);
            } else if ("global".equalsIgnoreCase(classification) && article.getLocation() == null) {
                filteredArticles.add(article);
            }
        }

        return filteredArticles;
    }
}