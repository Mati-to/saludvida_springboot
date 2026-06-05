package com.matiasac.saludvida_backend.model.dto.request;

import com.matiasac.saludvida_backend.model.enums.RolUsuario;

public record UsuarioCreateDTO(
        // TODO: Must implement - validaciones de entrada
        String nombre,
        String apellido,
        String username,
        String password,
        RolUsuario rol
) {
}
