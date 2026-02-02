package com.matiasac.saludvida_backend.model.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaMedicaListDTO(
        Long id,
        Long pacienteId,
        Long medicoId,
        String nombrePaciente,
        String nombreMedico,
        String especialidad,
        LocalDate fechaCita,
        LocalTime horaCita,
        String observaciones
) { }
