package com.matiasac.saludvida_backend.service;

import com.matiasac.saludvida_backend.model.dto.request.CitaMedicaRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaDetalleResponseDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaListDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ICitaMedicaService {

    List<CitaMedicaListDTO> findAll();

    List<LocalTime> findHorariosDisponiblesFecha(Long medicoId, LocalDate fecha);
    CitaMedicaDetalleResponseDTO findById(Long id);
    CitaMedicaDetalleResponseDTO create(CitaMedicaRequestDTO dto);
    CitaMedicaDetalleResponseDTO update(CitaMedicaRequestDTO dto, Long id);
    void deleteById(Long id);

}
