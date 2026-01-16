package com.matiasac.saludvida_backend.service;

import com.matiasac.saludvida_backend.model.dto.request.PacienteRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.PacienteResponseDTO;

import java.util.List;

public interface IPacienteService {

    List<PacienteResponseDTO> findAll();
    PacienteResponseDTO findById();
    PacienteResponseDTO create(PacienteRequestDTO dto);
    PacienteResponseDTO update(PacienteRequestDTO dtoUpdate, Long id);
    void deleteById(Long id);
}
