package com.matiasac.saludvida_backend.model.dto.request;

public record MedicoUpdateDTO(
        String nombre,
        String apellido,
        String telefono,
        Long especialidadId
) { }
