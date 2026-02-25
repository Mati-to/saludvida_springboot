package com.matiasac.saludvida_backend.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matiasac.saludvida_backend.exception.ValidacionNegocioException;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaMedicaRequestDTO(
        @NotNull(message = "Debe seleccionar un paciente")
        @Positive(message = "El ID del paciente debe ser válido")
        Long pacienteId,

        @NotNull(message = "Debe seleccionar un médico")
        @Positive(message = "El ID del médico debe ser válido")
        Long medicoId,

        @NotNull(message = "Debe seleccionar una fecha para la cita médica")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @FutureOrPresent(message = "La fecha debe ser para la fecha actual o posterior")
        LocalDate fechaCita,

        @NotNull(message = "Debe seleccionar una hora para la cita médica")
        @JsonFormat(pattern = "HH:mm")
        LocalTime horaCita,

        @Size(max = 250, message = "El mensaje debe tener máximo 250 caracteres")
        String observaciones
) {
        public CitaMedicaRequestDTO {
                observaciones = (observaciones != null) ? observaciones.trim() : null;
        }
}
