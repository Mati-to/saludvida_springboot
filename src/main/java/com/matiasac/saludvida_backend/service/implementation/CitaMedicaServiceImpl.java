package com.matiasac.saludvida_backend.service.implementation;

import com.matiasac.saludvida_backend.exception.NotFoundException;
import com.matiasac.saludvida_backend.model.dto.request.CitaMedicaRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaDetalleResponseDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaListDTO;
import com.matiasac.saludvida_backend.model.entity.CitaMedica;
import com.matiasac.saludvida_backend.model.entity.Medico;
import com.matiasac.saludvida_backend.model.entity.Paciente;
import com.matiasac.saludvida_backend.model.mapper.CitaMedicaMapper;
import com.matiasac.saludvida_backend.repository.ICitaMedicaRepository;
import com.matiasac.saludvida_backend.repository.IMedicoRepository;
import com.matiasac.saludvida_backend.repository.IPacienteRepository;
import com.matiasac.saludvida_backend.service.ICitaMedicaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitaMedicaServiceImpl implements ICitaMedicaService {
    private final ICitaMedicaRepository repository;
    private final IMedicoRepository medicoRepository;
    private final IPacienteRepository pacienteRepository;
    private final CitaMedicaMapper mapper;

    public CitaMedicaServiceImpl(ICitaMedicaRepository repository, IMedicoRepository medicoRepository, IPacienteRepository pacienteRepository, CitaMedicaMapper mapper) {
        this.repository = repository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaMedicaListDTO> findAll() {
        List<CitaMedica> citasMedicas = repository.findAll();

        return citasMedicas
                .stream()
                .map(mapper::toDtoList)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CitaMedicaDetalleResponseDTO findById(Long id) {
        CitaMedica citaMedica = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cita médica", id));
        return mapper.toDtoDetalle(citaMedica);
    }

    @Override
    @Transactional
    public CitaMedicaDetalleResponseDTO create(CitaMedicaRequestDTO dto) {
        Medico medico = medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new NotFoundException("Médico", dto.medicoId()));
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new NotFoundException("Paciente", dto.pacienteId()));

        CitaMedica citaMedica = mapper.toCitaMedica(
                dto,
                medico,
                paciente
        );
        repository.save(citaMedica);
        return mapper.toDtoDetalle(citaMedica);
    }

    @Override
    @Transactional
    public CitaMedicaDetalleResponseDTO update(CitaMedicaRequestDTO dto, Long id) {
        CitaMedica citaMedica = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cita médica", id));
        mapper.toUpdateCita(citaMedica, dto, citaMedica.getPaciente(), citaMedica.getMedico());
        return mapper.toDtoDetalle(citaMedica);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        CitaMedica citaMedica = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cita médica", id));
        repository.deleteById(citaMedica.getId());
    }
}
