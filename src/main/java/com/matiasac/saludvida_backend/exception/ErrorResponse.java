package com.matiasac.saludvida_backend.exception;


public record ErrorResponse(
        int status,
        String mensaje,
        String path
) { }
