package com.matiasac.saludvida_backend.exception;

import java.util.Map;

public record ValidacionErrorResponse(
        int status,
        String error,
        Map<String, String> mensajes,
        String path
) {
}
