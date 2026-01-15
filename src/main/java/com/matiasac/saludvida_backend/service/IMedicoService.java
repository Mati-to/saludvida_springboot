package com.matiasac.saludvida_backend.service;

import com.matiasac.saludvida_backend.model.dto.request.MedicoCreateDTO;
import com.matiasac.saludvida_backend.model.dto.request.MedicoUpdateDTO;
import com.matiasac.saludvida_backend.model.dto.response.MedicoResponseDTO;

import java.util.List;

public interface IMedicoService {

    List<MedicoResponseDTO> findAll();
    MedicoResponseDTO findById(Long id);
    MedicoResponseDTO create(MedicoCreateDTO dto);
    MedicoResponseDTO update(MedicoUpdateDTO dto, Long id);
    void deleteById(Long id);
}
