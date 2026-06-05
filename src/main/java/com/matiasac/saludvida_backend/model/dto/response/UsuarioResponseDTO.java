package com.matiasac.saludvida_backend.model.dto.response;

import com.matiasac.saludvida_backend.model.enums.RolUsuario;

public record UsuarioResponseDTO(
        String nombre,
        String apellido,
        String nombreApellido,
        RolUsuario rol
) { }
