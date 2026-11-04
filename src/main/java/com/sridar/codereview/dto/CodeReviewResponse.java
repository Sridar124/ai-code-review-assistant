package com.sridar.codereview.dto;

import java.util.List;

public record CodeReviewResponse(
        String language,
        String summary,
        int qualityScore,
        ComplexityAnalysis complexityAnalysis,
        List<String> bugs,
        List<String> performanceIssues,
        List<SecurityFinding> securityFindings,
        List<String> codeQuality,
        List<String> suggestions,
        String correctedCode
) {}