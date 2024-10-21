package org.example.services.claudeService;

import org.example.dtos.ClassificationResult;

public interface IClaudeService {
    ClassificationResult classifyNews(String content);
}