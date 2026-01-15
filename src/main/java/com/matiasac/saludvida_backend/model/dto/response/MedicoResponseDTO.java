package com.matiasac.saludvida_backend.model.dto.response;

import com.matiasac.saludvida_backend.model.dto.EspecialidadDTO;

public record MedicoResponseDTO(
        String nombre,
        String apellido,
        String correo,
        String rut,
        String telefono,
        EspecialidadDTO especialidadDto
) { }
