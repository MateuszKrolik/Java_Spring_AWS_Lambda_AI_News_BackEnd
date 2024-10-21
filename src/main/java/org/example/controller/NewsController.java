package org.example.controller;

import org.example.data.CityData;
import org.example.dtos.City;
import org.example.dtos.ClassificationResult;
import org.example.dtos.NewsArticle;
import org.example.services.articleService.IArticleService;
import org.example.services.claudeService.IClaudeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsController {
    private final IClaudeService openAIService;
    private final IArticleService articleService;

    public NewsController(IClaudeService openAIService, IArticleService articleService) {
        this.openAIService = openAIService;
        this.articleService = articleService;
    }

    @GetMapping("/cities")
    public List<City> getCities() {
        return CityData.getCities();
    }

    @PostMapping("/news")
    public List<NewsArticle> getNews(@RequestBody String content) {
        ClassificationResult classificationResult = openAIService.classifyNews(content);
        return articleService.fetchRelevantArticles(classificationResult);
    }
}