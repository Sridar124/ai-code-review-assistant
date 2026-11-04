package com.sridar.codereview.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sridar.codereview.dto.CodeReviewRequest;
import com.sridar.codereview.dto.CodeReviewResponse;
import com.sridar.codereview.rules.CodeReviewRule;

@Service
public class CodeReviewService {

    private final List<CodeReviewRule> rules;
    private final GroqAIService groqAIService;

    public CodeReviewService(
            List<CodeReviewRule> rules,
            GroqAIService groqAIService) {

        this.rules = rules;
        this.groqAIService = groqAIService;
    }

    public CodeReviewResponse review(CodeReviewRequest request) {

        List<String> ruleSuggestions = new ArrayList<>();

        // Execute rule-based checks
        for (CodeReviewRule rule : rules) {
            rule.evaluate(request.code(), ruleSuggestions);
        }

        // Perform AI-powered code review
        CodeReviewResponse aiReview = groqAIService.reviewCode(
                request.language(),
                request.code()
        );

        // Combine rule-based and AI suggestions
        List<String> allSuggestions =
                new ArrayList<>(ruleSuggestions);

        allSuggestions.addAll(aiReview.suggestions());

        return new CodeReviewResponse(
                request.language(),
                aiReview.summary(),
                aiReview.qualityScore(),
                aiReview.complexityAnalysis(),
                aiReview.bugs(),
                aiReview.performanceIssues(),
                aiReview.securityFindings(),
                aiReview.codeQuality(),
                allSuggestions,
                aiReview.correctedCode()
        );
    }
}