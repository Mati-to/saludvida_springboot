package com.matiasac.saludvida_backend.exception;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

public record ApiErrorResponse(
        int status,
        String mensaje,
        String path,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Map<String, List<String>> errores
) {
    public ApiErrorResponse(int status, String mensaje, String path) {
        this(status, mensaje, path, null);
    }
}
