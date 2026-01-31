package com.matiasac.saludvida_backend.model.dto.response;

import com.matiasac.saludvida_backend.model.enums.SexoPaciente;

import java.time.LocalDate;

public record PacienteResponseDTO(
        Long id,
        String nombre,
        String apellido,
        String rut,
        SexoPaciente sexo,
        String telefono,
        LocalDate fechaNacimiento,
        int edad
) {
}
