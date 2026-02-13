package com.matiasac.saludvida_backend.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matiasac.saludvida_backend.model.enums.SexoPaciente;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PacienteRequestDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$", message = "El nombre solo puede contener letras")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+$", message = "El apellido solo puede contener letras")
        String apellido,

        @NotBlank(message = "El RUT es obligatorio")
        @Pattern(regexp = "^[0-9]{7,8}-[0-9kK]$", message = "El formato del RUT es inválido (ej: 12345678-K o 12345678-9)")
        String rut,

        @Size(max = 11, message = "Formato inválido. No puede tener más de 11 dígitos")
        @Pattern(regexp = "^(9[0-9]{8,11}|)$", message = "El teléfono debe empezar con 9 y debe tener 9 dígitos")
        String telefono,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Past(message = "La fecha debe ser anterior a hoy")
        LocalDate fechaNacimiento,

        @NotNull(message = "Debe seleccionar el sexo")
        SexoPaciente sexo
) {
        public PacienteRequestDTO {
                nombre = (nombre != null) ? nombre.trim() : null;
                apellido = (apellido != null) ? apellido.trim() : null;
                rut = (rut != null) ? rut.trim().toUpperCase() : null;
                telefono = (telefono != null) ? telefono.trim() : null;
        }
}
