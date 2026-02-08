package com.matiasac.saludvida_backend.model.dto.request;

import com.matiasac.saludvida_backend.config.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MedicoCreateDTO(
        @NotBlank(message = "El nombre es obligatorio.", groups = ValidationGroups.Primero.class)
        @Size(min = 3,
                max = 50,
                message = "Debe contener entre 3 y 50 caracteres.",
                groups = ValidationGroups.Segundo.class)
        @Pattern(
                regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$",
                message = "El nombre solo puede contener letras.",
                groups = ValidationGroups.Tercero.class)
        String nombre,

        @Pattern(
                regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$",
                message = "El apellido solo puede contener letras.")
        @Size(max = 50, message = "Solo puede contener 50 caracteres.")
        @NotBlank(message = "El apellido es obligatorio.")
        String apellido,

        @NotBlank(message = "El correo es obligatorio.")
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "El formato del correo es inválido."
        )
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
