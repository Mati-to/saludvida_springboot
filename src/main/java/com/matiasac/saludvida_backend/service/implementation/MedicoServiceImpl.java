package com.matiasac.saludvida_backend.service.implementation;

import com.matiasac.saludvida_backend.exception.NotFoundException;
import com.matiasac.saludvida_backend.model.dto.request.MedicoCreateDTO;
import com.matiasac.saludvida_backend.model.dto.request.MedicoUpdateDTO;
import com.matiasac.saludvida_backend.model.dto.response.MedicoResponseDTO;
import com.matiasac.saludvida_backend.model.entity.Especialidad;
import com.matiasac.saludvida_backend.model.entity.Medico;
import com.matiasac.saludvida_backend.model.mapper.MedicoMapper;
import com.matiasac.saludvida_backend.repository.IEspecialidadRepository;
import com.matiasac.saludvida_backend.repository.IMedicoRepository;
import com.matiasac.saludvida_backend.service.IMedicoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoServiceImpl implements IMedicoService {
    private final IMedicoRepository repository;
    private final IEspecialidadRepository especialidadRepository;
    private final MedicoMapper mapper;

    public MedicoServiceImpl(
            IMedicoRepository repository, IEspecialidadRepository especialidadRepository, MedicoMapper mapper
    ) {
        this.repository = repository;
        this.especialidadRepository = especialidadRepository;
        this.mapper = mapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<MedicoResponseDTO> findAll() {
        List<Medico> medicos = repository.findAll();

        return medicos.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public MedicoResponseDTO findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public MedicoResponseDTO create(MedicoCreateDTO dtoMedico) {
        Especialidad especialidad = especialidadRepository
                .findById(dtoMedico.especialidadId())
                .orElseThrow(() -> new NotFoundException("Especialidad", dtoMedico.especialidadId()));

        Medico medico = mapper.toMedico(dtoMedico, especialidad);
        repository.save(medico);
        return mapper.toDto(medico);
    }

    @Override
    @Transactional
    public MedicoResponseDTO update(MedicoUpdateDTO dto, Long id) {
        Medico medico = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Médico", id));
        Especialidad especialidad = especialidadRepository.findById(dto.especialidadId())
                        .orElseThrow(() -> new NotFoundException("Especialidad", dto.especialidadId()));

        mapper.toUpdateMedico(medico, dto, especialidad);
        return mapper.toDto(medico);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Medico medico = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Médico", id));
        repository.deleteById(medico.getId());
    }
}
