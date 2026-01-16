package com.matiasac.saludvida_backend.repository;

import com.matiasac.saludvida_backend.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
}
