package com.matiasac.saludvida_backend.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entidad, Long id) {
        super(entidad + " con ID: " + id + " no fue encontrado.");
    }
}
