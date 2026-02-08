package com.matiasac.saludvida_backend.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MedicoCreateDTO(
        @Pattern(
                regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$",
                message = "El nombre solo puede contener letras.")
        @Size(max = 50, message = "Solo puede contener 50 caracteres.")
        @NotBlank(message = "El nombre es obligatorio.")
        String nombre,

        @Pattern(
                regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$",
                message = "El apellido solo puede contener letras.")
        @Size(max = 50, message = "Solo puede contener 50 caracteres.")
        @NotBlank(message = "El apellido es obligatorio.")
        String apellido,

        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$",
                message = "El formato del correo es inválido."
        )
        @NotBlank(message = "El correo es obligatorio.")
        String correo,

        @Pattern(
                regexp = "^[0-9]{7,8}-[0-9kK]$",
                message = "El formato del RUT es inválido (ej: 12345678-K o 12345678-9)."
        )
        @NotBlank(message = "El RUT es obligatorio.")
        String rut,

        @Pattern(
                regexp = "^(9[0-9]{8}|)$",
                message = "El teléfono debe empezar con 9 y debe tener 9 dígitos en total."
        )
        String telefono,

        Long especialidadId
) { }
