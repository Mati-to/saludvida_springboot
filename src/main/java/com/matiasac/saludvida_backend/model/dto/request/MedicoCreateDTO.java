package com.matiasac.saludvida_backend.model.dto.request;

import jakarta.validation.constraints.*;

public record MedicoCreateDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$", message = "El nombre solo puede contener letras")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$", message = "El apellido solo puede contener letras")
        String apellido,

        @NotBlank(message = "El correo es obligatorio")
        @Size(max = 100, message = "No debe tener más de 100 caracteres")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El formato del correo es inválido")
        String correo,

        @NotBlank(message = "El RUT es obligatorio")
        @Pattern(regexp = "^[0-9]{7,8}-[0-9kK]$", message = "El formato del RUT es inválido (ej: 12345678-K o 12345678-9)")
        String rut,

        @Size(max = 11, message = "Formato inválido. No puede tener más de 11 dígitos")
        @Pattern(regexp = "^(9[0-9]{8,11}|)$", message = "El teléfono debe empezar con 9 y debe tener 9 dígitos")
        String telefono,

        @NotNull(message = "Debe seleccionar una especialidad")
        @Positive(message = "El ID de la especialidad debe ser válido")
        Long especialidadId
) {
        public MedicoCreateDTO {
                nombre = (nombre != null) ? nombre.trim() : null;
                apellido = (apellido != null) ? apellido.trim() : null;
                correo = (correo != null) ? correo.trim() : null;
                rut = (rut != null) ? rut.trim().toUpperCase() : null;
                telefono = (telefono != null) ? telefono.trim() : null;
        }
}
