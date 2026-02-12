package com.matiasac.saludvida_backend.model.dto.request;

import jakarta.validation.constraints.*;

public record MedicoCreateDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$", message = "El nombre solo puede contener letras")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 3, max = 50, message = "Solo puede contener 50 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$", message = "El apellido solo puede contener letras")
        String apellido,

        @NotBlank(message = "El correo es obligatorio.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El formato del correo es inválido")
        String correo,

        @NotBlank(message = "El RUT es obligatorio.")
        @Pattern(regexp = "^[0-9]{7,8}-[0-9kK]$", message = "El formato del RUT es inválido (ej: 12345678-K o 12345678-9)")
        String rut,

        @Pattern(regexp = "^(9[0-9]{8}|)$", message = "El teléfono debe empezar con 9 y debe tener 9 dígitos en total")
        String telefono,

        @NotNull(message = "Debe seleccionar una especialidad")
        @Positive(message = "El ID de la especialidad debe ser válido")
        Long especialidadId
) { }
