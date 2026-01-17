package com.matiasac.saludvida_backend.repository;

import com.matiasac.saludvida_backend.model.entity.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICitaMedicaRepository extends JpaRepository<CitaMedica, Long> {
}
