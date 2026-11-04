
package com.sridar.codereview.service;

import com.sridar.codereview.dto.CodeReviewRequest;
import com.sridar.codereview.dto.CodeReviewResponse;
import com.sridar.codereview.dto.ComplexityAnalysis;
import com.sridar.codereview.dto.SecurityFinding;
import com.sridar.codereview.rules.CodeReviewRule;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CodeReviewServiceTest {

    @Test
    void shouldCombineRuleAndAiSuggestions() {

        CodeReviewRule rule = mock(CodeReviewRule.class);
        GroqAIService groqAIService = mock(GroqAIService.class);

        doAnswer(invocation -> {
            List<String> suggestions = invocation.getArgument(1);
            suggestions.add("Avoid using magic numbers");
            return null;
        }).when(rule).evaluate(anyString(), anyList());

        ComplexityAnalysis complexity = new ComplexityAnalysis(
                "O(n)",
                "O(1)",
                "Linear Search",
                "Iterates through the input once.",
                "HIGH"
        );

        CodeReviewResponse aiResponse = new CodeReviewResponse(
                "Java",
                "Code review completed.",
                85,
                complexity,
                List.of(),
                List.of(),
                List.<SecurityFinding>of(),
                List.of("Use meaningful variable names"),
                List.of("Add input validation"),
                "public class Demo {}"
        );

        when(groqAIService.reviewCode(anyString(), anyString()))
                .thenReturn(aiResponse);

        CodeReviewService service = new CodeReviewService(
                List.of(rule),
                groqAIService
        );

        CodeReviewRequest request = new CodeReviewRequest(
                "Java",
                "public class Demo {}"
        );

        CodeReviewResponse result = service.review(request);

        assertNotNull(result);
        assertEquals(85, result.qualityScore());

        assertEquals(2, result.suggestions().size());

        assertTrue(result.suggestions().contains(
                "Avoid using magic numbers"
        ));

        assertTrue(result.suggestions().contains(
                "Add input validation"
        ));

        ArgumentCaptor<List<String>> captor =
                ArgumentCaptor.forClass(List.class);

        verify(rule).evaluate(
                eq(request.code()),
                captor.capture()
        );

        assertTrue(captor.getValue().contains(
                "Avoid using magic numbers"
        ));

        verify(groqAIService).reviewCode(
                "Java",
                request.code()
        );
    }
}
