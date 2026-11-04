
package com.sridar.codereview.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import com.sridar.codereview.dto.CodeReviewRequest;
import com.sridar.codereview.dto.CodeReviewResponse;
import com.sridar.codereview.service.CodeReviewService;

@RestController
@RequestMapping("/api/reviews")
@Validated
public class CodeReviewController {

    private final CodeReviewService codeReviewService;

    public CodeReviewController(
            CodeReviewService codeReviewService) {
        this.codeReviewService = codeReviewService;
    }

    @PostMapping
    public ResponseEntity<CodeReviewResponse> reviewCode(
            @Valid @RequestBody CodeReviewRequest request) {

        CodeReviewResponse response =
                codeReviewService.review(request);

        return ResponseEntity.ok(response);
    }
}
