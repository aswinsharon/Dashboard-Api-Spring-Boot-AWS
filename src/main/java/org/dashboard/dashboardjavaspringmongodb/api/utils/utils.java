package org.dashboard.dashboardjavaspringmongodb.api.utils;

import org.dashboard.dashboardjavaspringmongodb.api.dto.ApiResponse;
import org.dashboard.dashboardjavaspringmongodb.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.dashboard.dashboardjavaspringmongodb.api.dto.ValidationRule;

public class utils {

    public static ResponseEntity<ApiResponse> jsonResponse(int statusCode, String message) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(statusCode, message, null),
                HttpStatus.valueOf(statusCode));
    }

    public static ResponseEntity<ApiResponse> jsonResponse(int statusCode, String message, Object data) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(statusCode, message, data),
                HttpStatus.valueOf(statusCode));
    }

    public static boolean validateNewUserObjectFields(User newUserObject) {
        List<String> requiredFields = List.of(
                "username",
                "email",
                "isAdmin",
                "isActive",
                "password",
                "address",
                "phone",
                "img");

        return requiredFields.stream()
                .allMatch(field -> validateField(newUserObject, field));
    }

    public static boolean validateNewUserObjectValues(User newUserObject) {
        List<ValidationRule> requiredFields = List.of(
                new ValidationRule("username", value -> value instanceof String && !((String) value).isEmpty()),
                new ValidationRule("email",
                        value -> value instanceof String && ((String) value).matches("\\S+@\\S+\\.\\S+")),
                new ValidationRule("isAdmin",
                        value -> value instanceof String
                                && ("yes".equalsIgnoreCase((String) value) || "no".equalsIgnoreCase((String) value))),
                new ValidationRule("isActive",
                        value -> value instanceof String
                                && ("yes".equalsIgnoreCase((String) value) || "no".equalsIgnoreCase((String) value))),
                new ValidationRule("password", value -> value instanceof String && ((String) value).length() >= 8),
                new ValidationRule("address", value -> value instanceof String && !((String) value).isEmpty()),
                new ValidationRule("phone",
                        value -> value instanceof String && ((String) value).matches("\\d{3}-\\d{3}-\\d{4}")),
                new ValidationRule("img", value -> value instanceof String && !((String) value).isEmpty()));

        for (ValidationRule rule : requiredFields) {
            if (!rule.getValidator().test(getFieldValue(newUserObject, rule.getName()))) {
                return false; // If any rule fails, the object is considered invalid
            }
        }

        return true;
    }

    private static boolean validateField(User user, String fieldName) {
        Object value = getFieldValue(user, fieldName);
        return value != null && !value.toString().isEmpty();
    }

    private static Object getFieldValue(User user, String fieldName) {

        return switch (fieldName) {
            case "username" -> user.getUsername();
            case "email" -> user.getEmail();
            case "isAdmin" -> user.getIsAdmin();
            case "isActive" -> user.getIsActive();
            case "password" -> user.getPassword();
            case "address" -> user.getAddress();
            case "phone" -> user.getPhone();
            case "img" -> user.getImg();
            case "createdAt" -> user.getCreatedAt();
            case "updatedAt" -> user.getUpdatedAt();
            default -> null;
        };
    }

}