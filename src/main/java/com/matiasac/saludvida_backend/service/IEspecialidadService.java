package com.matiasac.saludvida_backend.service;


import com.matiasac.saludvida_backend.model.dto.EspecialidadDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IEspecialidadService {

    List<EspecialidadDTO> findAll();

    EspecialidadDTO findById(Long id);

    EspecialidadDTO create(EspecialidadDTO dto);

    EspecialidadDTO update(EspecialidadDTO dto, Long id);

    void deleteById(Long id);

}
