package com.matiasac.saludvida_backend.model.mapper;

import com.matiasac.saludvida_backend.model.dto.request.CitaMedicaRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaDetalleResponseDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaListDTO;
import com.matiasac.saludvida_backend.model.entity.CitaMedica;
import com.matiasac.saludvida_backend.model.entity.Medico;
import com.matiasac.saludvida_backend.model.entity.Paciente;
import org.springframework.stereotype.Component;

@Component
public class CitaMedicaMapper {
    private final MedicoMapper medicoMapper;
    private final PacienteMapper pacienteMapper;

    public CitaMedicaMapper(
            MedicoMapper medicoMapper,
            PacienteMapper pacienteMapper
    ) {
        this.medicoMapper = medicoMapper;
        this.pacienteMapper = pacienteMapper;
    }

    public CitaMedica toCitaMedica(
            CitaMedicaRequestDTO dto,
            Medico medico,
            Paciente paciente
    ) {
        return new CitaMedica(
                paciente,
                medico,
                dto.fechaCita(),
                dto.horaCita()
        );
    }

    public CitaMedicaListDTO toDtoList(CitaMedica citaMedica) {
        return new CitaMedicaListDTO(
                citaMedica.getPaciente().getNombreCompleto(),
                citaMedica.getMedico().getNombreCompleto(),
                citaMedica.getMedico().getEspecialidad().getNombre(),
                citaMedica.getFechaCita(),
                citaMedica.getHoraCita()
        );
    }

    public CitaMedicaDetalleResponseDTO toDtoDetalle(CitaMedica citaMedica) {
        return new CitaMedicaDetalleResponseDTO(
                pacienteMapper.toDto(citaMedica.getPaciente()),
                medicoMapper.toDto(citaMedica.getMedico()),
                citaMedica.getFechaCita(),
                citaMedica.getHoraCita(),
                citaMedica.getObservaciones()
        );
    }

    public void toUpdateCita(
            CitaMedica citaMedica,
            CitaMedicaRequestDTO dto,
            Paciente paciente,
            Medico medico
    ) {
        citaMedica.setPaciente(paciente);
        citaMedica.setMedico(medico);
        citaMedica.setFechaCita(dto.fechaCita());
        citaMedica.setHoraCita(dto.horaCita());
        citaMedica.setObservaciones(dto.observaciones());
    }

}
