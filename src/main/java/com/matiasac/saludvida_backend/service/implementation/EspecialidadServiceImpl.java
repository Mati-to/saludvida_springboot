package com.matiasac.saludvida_backend.service.implementation;

import com.matiasac.saludvida_backend.exception.NotFoundException;
import com.matiasac.saludvida_backend.exception.RecursoDuplicadoException;
import com.matiasac.saludvida_backend.exception.RecursoEnUsoException;
import com.matiasac.saludvida_backend.model.dto.EspecialidadDTO;
import com.matiasac.saludvida_backend.model.dto.response.EspecialidadResponseDTO;
import com.matiasac.saludvida_backend.model.entity.Especialidad;
import com.matiasac.saludvida_backend.model.mapper.EspecialidadMapper;
import com.matiasac.saludvida_backend.repository.IEspecialidadRepository;
import com.matiasac.saludvida_backend.repository.IMedicoRepository;
import com.matiasac.saludvida_backend.service.IEspecialidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {
    private final IEspecialidadRepository repository;
    private final IMedicoRepository medicoRepository;
    private final EspecialidadMapper mapper;

    public EspecialidadServiceImpl(IEspecialidadRepository repository, IMedicoRepository medicoRepository, EspecialidadMapper mapper) {
        this.repository = repository;
        this.medicoRepository = medicoRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EspecialidadResponseDTO> findAll() {
        List<Especialidad> especialidades = repository.findAll();

        return especialidades.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EspecialidadResponseDTO findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public EspecialidadResponseDTO create(EspecialidadDTO dtoEspecialidad) {
        if (repository.existsByNombre(dtoEspecialidad.nombre())) {
            throw new RecursoDuplicadoException("Ya existe una especialidad con este nombre");
        }

        Especialidad especialidad = mapper.toEspecialidad(dtoEspecialidad);
        repository.save(especialidad);
        return mapper.toDto(especialidad);
    }

    @Override
    @Transactional
    public EspecialidadResponseDTO update(EspecialidadDTO entidadDto, Long id) {
        Especialidad especialidad = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Especialidad", id));
        if (repository.existsByNombre(entidadDto.nombre()) && !especialidad.getId().equals(id)) {
            throw new RecursoDuplicadoException("Ya existe una especialidad con este nombre");
        }
        mapper.toUpdateEspecialidad(especialidad, entidadDto);
        return mapper.toDto(especialidad);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Especialidad especialidad = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Especialidad", id));

        if (medicoRepository.existsByEspecialidadId(id)) {
            throw new RecursoEnUsoException(
                    "No se puede eliminar: existen médicos asociados a esta especialidad");
        }
        repository.deleteById(especialidad.getId());
    }
}
