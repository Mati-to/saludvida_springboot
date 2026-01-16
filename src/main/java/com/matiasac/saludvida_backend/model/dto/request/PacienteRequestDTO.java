package com.matiasac.saludvida_backend.model.dto.request;

import com.matiasac.saludvida_backend.model.enums.SexoPaciente;

import java.time.LocalDate;

public record PacienteRequestDTO(
        String nombre,
        String apellido,
        String rut,
        String telefono,
        LocalDate fechaNacimiento,
        SexoPaciente sexo
) {
}
