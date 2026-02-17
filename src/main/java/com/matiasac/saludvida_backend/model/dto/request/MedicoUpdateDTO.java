package com.matiasac.saludvida_backend.model.dto.request;

import jakarta.validation.constraints.*;

public record MedicoUpdateDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$", message = "El nombre solo puede contener letras")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$", message = "El apellido solo puede contener letras")
        String apellido,

        @Size(max = 11, message = "Formato inválido. No puede tener más de 11 dígitos")
        @Pattern(regexp = "^(9[0-9]{8,11}|)$", message = "El teléfono debe empezar con 9 y debe tener 9 dígitos")
        String telefono,

        @NotNull(message = "Debe seleccionar una especialidad")
        @Positive(message = "El ID de la especialidad debe ser válido")
        Long especialidadId
) {
        public MedicoUpdateDTO {
                nombre = (nombre != null) ? nombre.trim() : null;
                apellido = (apellido != null) ? apellido.trim() : null;
                telefono = (telefono != null) ? telefono.trim() : null;
        }
}
