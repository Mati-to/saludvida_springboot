package com.matiasac.saludvida_backend.model.dto.response;


public record MedicoResponseDTO(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String rut,
        String telefono,
        String nombreEspecialidad
) { }
