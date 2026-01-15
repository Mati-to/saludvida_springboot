package com.matiasac.saludvida_backend.repository;

import com.matiasac.saludvida_backend.model.dto.EspecialidadDTO;
import com.matiasac.saludvida_backend.model.entity.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspecialidadRepository extends JpaRepository<Especialidad, Long> {

    EspecialidadDTO findByNombre(String nombre);

}
