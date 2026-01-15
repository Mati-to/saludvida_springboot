package com.matiasac.saludvida_backend.model.mapper;

import com.matiasac.saludvida_backend.model.dto.EspecialidadDTO;
import com.matiasac.saludvida_backend.model.entity.Especialidad;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadMapper {

    public Especialidad toEspecialidad(EspecialidadDTO dto) {
        return new Especialidad(dto.nombre());
    }

    public EspecialidadDTO toDto(Especialidad especialidad) {
        return new EspecialidadDTO(especialidad.getNombre());
    }

    public void toUpdateEspecialidad(Especialidad especialidad, EspecialidadDTO dto) {
        especialidad.setNombre(dto.nombre());
    }
}
