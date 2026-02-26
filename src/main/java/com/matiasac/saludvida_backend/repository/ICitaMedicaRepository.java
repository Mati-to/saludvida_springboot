package com.matiasac.saludvida_backend.repository;

import com.matiasac.saludvida_backend.model.entity.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ICitaMedicaRepository extends JpaRepository<CitaMedica, Long> {

    @Query("SELECT c.horaCita FROM CitaMedica c WHERE c.medico.id = :medicoId AND c.fechaCita = :fecha")
    List<LocalTime> findAllHorasByMedicoIdAndFechaCita(Long medicoId, LocalDate fecha);

    boolean existsByMedicoIdAndFechaCitaAndHoraCita(Long medicoId, LocalDate fecha, LocalTime hora);
    boolean existsByPacienteIdAndFechaCitaAndHoraCita(Long pacienteId, LocalDate fecha, LocalTime hora);
    boolean existsByPacienteIdAndMedicoIdAndFechaCita(Long pacienteId, Long medicoId, LocalDate fecha);
}
