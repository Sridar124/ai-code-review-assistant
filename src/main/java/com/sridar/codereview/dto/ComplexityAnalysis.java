package com.sridar.codereview.dto;

public record ComplexityAnalysis(
        String timeComplexity,
        String spaceComplexity,
        String algorithm,
        String explanation,
        String confidence
) {}