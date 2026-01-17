package com.matiasac.saludvida_backend.service;

import com.matiasac.saludvida_backend.model.dto.request.CitaMedicaRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaDetalleResponseDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaListDTO;

import java.util.List;

public interface ICitaMedicaService {

    List<CitaMedicaListDTO> findAll();
    CitaMedicaDetalleResponseDTO findById(Long id);
    CitaMedicaDetalleResponseDTO create(CitaMedicaRequestDTO dto);
    CitaMedicaDetalleResponseDTO update(CitaMedicaRequestDTO dto, Long id);
    void deleteById(Long id);

}
