package com.matiasac.saludvida_backend.service.implementation;

import com.matiasac.saludvida_backend.exception.NotFoundException;
import com.matiasac.saludvida_backend.model.dto.request.PacienteRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.PacienteResponseDTO;
import com.matiasac.saludvida_backend.model.entity.Paciente;
import com.matiasac.saludvida_backend.model.mapper.PacienteMapper;
import com.matiasac.saludvida_backend.repository.IPacienteRepository;
import com.matiasac.saludvida_backend.service.IPacienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService {
    private final IPacienteRepository repository;
    private final PacienteMapper mapper;

    public PacienteServiceImpl(IPacienteRepository repository, PacienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> findAll() {
        List<Paciente> pacientes = repository.findAll();

        return pacientes.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public PacienteResponseDTO findById() {
        return null;
    }

    @Override
    @Transactional
    public PacienteResponseDTO create(PacienteRequestDTO dto) {
        Paciente paciente = mapper.toPaciente(dto);
        repository.save(paciente);
        return mapper.toDto(paciente);
    }

    @Override
    @Transactional
    public PacienteResponseDTO update(PacienteRequestDTO dtoUpdate, Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente", id));
        mapper.toUpdatePaciente(paciente, dtoUpdate);
        return mapper.toDto(paciente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente", id));
        repository.deleteById(id);
    }
}
