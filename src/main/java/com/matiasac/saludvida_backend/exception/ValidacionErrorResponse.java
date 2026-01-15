package com.matiasac.saludvida_backend.exception;

import java.util.List;

public record ValidacionErrorResponse(
        int status,
        String error,
        List<String> mensajes,
        String path
) {
}
