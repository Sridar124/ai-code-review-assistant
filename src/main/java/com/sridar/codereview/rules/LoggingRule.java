
package com.sridar.codereview.rules;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LoggingRule implements CodeReviewRule {

    private static final String PRINT_STATEMENT =
            "System.out.println";

    @Override
    public void evaluate(String code, List<String> suggestions) {

        if (code.contains(PRINT_STATEMENT)) {
            suggestions.add(
                    "Consider using a logging framework for application logs."
            );
        }
    }
}
