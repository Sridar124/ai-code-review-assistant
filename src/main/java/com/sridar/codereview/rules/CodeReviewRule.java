
package com.sridar.codereview.rules;

import java.util.List;

public interface CodeReviewRule {

    void evaluate(String code, List<String> suggestions);
}
