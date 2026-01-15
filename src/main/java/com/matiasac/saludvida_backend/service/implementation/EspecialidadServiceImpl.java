package com.matiasac.saludvida_backend.service.implementation;

import com.matiasac.saludvida_backend.exception.NotFoundException;
import com.matiasac.saludvida_backend.model.dto.EspecialidadDTO;
import com.matiasac.saludvida_backend.model.entity.Especialidad;
import com.matiasac.saludvida_backend.model.mapper.EspecialidadMapper;
import com.matiasac.saludvida_backend.repository.IEspecialidadRepository;
import com.matiasac.saludvida_backend.service.IEspecialidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {
    private final IEspecialidadRepository repository;
    private final EspecialidadMapper mapper;

    public EspecialidadServiceImpl(IEspecialidadRepository repository, EspecialidadMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EspecialidadDTO> findAll() {
        List<Especialidad> especialidades = repository.findAll();

        return especialidades.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EspecialidadDTO findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public EspecialidadDTO create(EspecialidadDTO dtoEspecialidad) {
        Especialidad especialidad = mapper.toEspecialidad(dtoEspecialidad);
        repository.save(especialidad);
        return mapper.toDto(especialidad);
    }

    @Override
    @Transactional
    public EspecialidadDTO update(EspecialidadDTO entidadDto, Long id) {
        Especialidad especialidad = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Especialidad", id));
        mapper.toUpdateEspecialidad(especialidad, entidadDto);
        return mapper.toDto(especialidad);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Especialidad especialidad = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Especialidad", id));
        repository.deleteById(especialidad.getId());
    }
}
