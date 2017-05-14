package com.ai.opt.sol.core.constants;

/**
 * Created by astraea on 2015/9/2.
 */
public enum SearchField {
    SONAR_RULE_TITLE("ruleTitle"), API_OWNER("owner");

    private String fieldName;

    SearchField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String fieldName() {
        return fieldName;
    }
}
