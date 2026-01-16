package com.matiasac.saludvida_backend.model.dto.response;

import com.matiasac.saludvida_backend.model.enums.SexoPaciente;

public record PacienteResponseDTO(
        String nombre,
        String apellido,
        String rut,
        SexoPaciente sexo,
        String telefono,
        int edad
) {
}
