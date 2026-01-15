package com.matiasac.saludvida_backend.repository;

import com.matiasac.saludvida_backend.model.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicoRepository extends JpaRepository<Medico, Long> {
}
