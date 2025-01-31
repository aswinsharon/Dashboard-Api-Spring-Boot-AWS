package org.dashboard.dashboardjavaspringmongodb.api.dto;

public class ValidationRule {
    private final String name;
    private final java.util.function.Predicate<Object> validator;

    public ValidationRule(String name, java.util.function.Predicate<Object> validator) {
        this.name = name;
        this.validator = validator;
    }

    public String getName() {
        return name;
    }

    public java.util.function.Predicate<Object> getValidator() {
        return validator;
    }
}
