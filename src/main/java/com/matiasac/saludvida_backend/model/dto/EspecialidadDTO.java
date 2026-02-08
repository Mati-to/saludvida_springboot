package com.matiasac.saludvida_backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EspecialidadDTO(

        @Size(
                max = 100,
                message = "El nombre no puede superar los 100 caracteres.")
        @Pattern(
                regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$",
                message = "El nombre solo puede contener letras y espacios.")
        @NotBlank(message = "El nombre de la especialidad es obligatorio.")
        String nombre
) { }
