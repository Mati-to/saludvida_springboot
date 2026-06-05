package com.matiasac.saludvida_backend.model.dto.request;

public record UsuarioUpdateDTO(
        // TODO: Must implement - Validaciones de entrada
        String nombre,
        String apellido,
        String password
) {
}
