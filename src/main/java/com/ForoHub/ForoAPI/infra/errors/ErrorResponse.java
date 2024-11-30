package com.ForoHub.ForoAPI.infra.errors;

public record ErrorResponse(
        int status,
        String message,
        String error,
        Long timestamp
) {
    public ErrorResponse(int value, String message, String entityNotFound) {
        this(value, message, entityNotFound, System.currentTimeMillis());
    }
}
