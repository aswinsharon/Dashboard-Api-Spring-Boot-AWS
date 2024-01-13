package org.dashboard.dashboardjavaspringmongodb.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse {
    private final int statusCode;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Object data;

    public ApiResponse(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
