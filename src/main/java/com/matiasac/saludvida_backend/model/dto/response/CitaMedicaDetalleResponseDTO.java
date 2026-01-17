package com.matiasac.saludvida_backend.model.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaMedicaDetalleResponseDTO(
        PacienteResponseDTO pacienteDto,
        MedicoResponseDTO medicoDto,
        LocalDate fechaCita,
        LocalTime horaCita,
        String observaciones
) { }
