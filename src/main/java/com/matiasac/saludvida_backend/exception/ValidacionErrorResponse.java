package com.matiasac.saludvida_backend.exception;

import java.util.List;
import java.util.Map;

public record ValidacionErrorResponse(
        int status,
        String error,
        Map<String, List<String>> mensajes,
        String path
) {
}
