package com.matiasac.saludvida_backend.service;


import com.matiasac.saludvida_backend.model.dto.EspecialidadDTO;
import com.matiasac.saludvida_backend.model.dto.response.EspecialidadResponseDTO;

import java.util.List;

public interface IEspecialidadService {

    List<EspecialidadResponseDTO> findAll();

    EspecialidadResponseDTO findById(Long id);

    EspecialidadResponseDTO create(EspecialidadDTO dto);

    EspecialidadResponseDTO update(EspecialidadDTO dto, Long id);

    void deleteById(Long id);

}
