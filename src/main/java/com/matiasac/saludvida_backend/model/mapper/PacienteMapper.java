package com.matiasac.saludvida_backend.model.mapper;

import com.matiasac.saludvida_backend.model.dto.request.PacienteRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.PacienteResponseDTO;
import com.matiasac.saludvida_backend.model.entity.Paciente;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class PacienteMapper {

    public Paciente toPaciente(PacienteRequestDTO dto) {
        return new Paciente(
                dto.nombre(),
                dto.apellido(),
                dto.rut(),
                dto.fechaNacimiento(),
                dto.sexo()
        );
    }

    public PacienteResponseDTO toDto(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getRut(),
                paciente.getSexo(),
                paciente.getTelefono(),
                calcularEdad(paciente.getFechaNacimiento())
        );
    }

    public void toUpdatePaciente(Paciente paciente, PacienteRequestDTO dto) {
        paciente.setNombre(dto.nombre());
        paciente.setApellido(dto.apellido());
        paciente.setRut(dto.rut());
        paciente.setSexo(dto.sexo());
        paciente.setFechaNacimiento(dto.fechaNacimiento());
        paciente.setTelefono(dto.telefono());
    }

    public int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

}
