package com.matiasac.saludvida_backend.model.mapper;

import com.matiasac.saludvida_backend.model.dto.request.MedicoCreateDTO;
import com.matiasac.saludvida_backend.model.dto.request.MedicoUpdateDTO;
import com.matiasac.saludvida_backend.model.dto.response.MedicoResponseDTO;
import com.matiasac.saludvida_backend.model.entity.Especialidad;
import com.matiasac.saludvida_backend.model.entity.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public Medico toMedico(MedicoCreateDTO dto, Especialidad especialidad) {
        return new Medico(
                dto.nombre(),
                dto.apellido(),
                dto.rut(),
                dto.correo(),
                especialidad
        );
    }

    public MedicoResponseDTO toDto(Medico medico) {
        return new MedicoResponseDTO(
                medico.getId(),
                medico.getNombre(),
                medico.getApellido(),
                medico.getCorreo(),
                medico.getRut(),
                medico.getTelefono(),
                medico.getEspecialidad().getNombre()
        );
    }

    public void toUpdateMedico(Medico medico, MedicoUpdateDTO dto, Especialidad especialidad) {
        medico.setNombre(dto.nombre());
        medico.setApellido(dto.apellido());
        medico.setTelefono(dto.telefono());
        medico.setEspecialidad(especialidad);
    }
}
