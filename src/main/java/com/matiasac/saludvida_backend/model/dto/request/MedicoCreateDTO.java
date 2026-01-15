package com.matiasac.saludvida_backend.model.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MedicoCreateDTO(
        String nombre,
        String apellido,

        @Email(message = "El formato del correo es inválido.")
        @NotBlank(message = "El correo es obligatorio.")
        String correo,

        String rut,
        String telefono,
        Long especialidadId
) { }
