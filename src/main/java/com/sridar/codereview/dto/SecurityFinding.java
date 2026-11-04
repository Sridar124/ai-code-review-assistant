package com.sridar.codereview.dto;

public record SecurityFinding(
        String vulnerability,
        String severity,
        String description,
        String affectedCode,
        String recommendation
) {}