
package com.sridar.codereview.rules;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TodoRule implements CodeReviewRule {

    private static final String TODO_MARKER = "TODO";

    @Override
    public void evaluate(String code, List<String> suggestions) {

        if (code.contains(TODO_MARKER)) {
            suggestions.add(
                    "Resolve TODO comments before considering the code complete."
            );
        }
    }
}
