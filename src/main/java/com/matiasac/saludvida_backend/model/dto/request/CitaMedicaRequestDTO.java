package com.matiasac.saludvida_backend.model.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaMedicaRequestDTO(
        Long pacienteId,
        Long medicoId,
        LocalDate fechaCita,
        LocalTime horaCita,
        String observaciones
) { }
