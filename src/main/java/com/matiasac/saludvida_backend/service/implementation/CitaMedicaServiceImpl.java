package com.matiasac.saludvida_backend.service.implementation;

import com.matiasac.saludvida_backend.exception.NotFoundException;
import com.matiasac.saludvida_backend.exception.ValidacionNegocioException;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public List<LocalTime> findHorariosDisponiblesFecha(Long medicoId, LocalDate fecha) {
        medicoRepository.findById(medicoId)
                .orElseThrow(() -> new NotFoundException("Médico", medicoId));

        if (fecha.isBefore(LocalDate.now())) {
            throw new ValidacionNegocioException("No se puede agendar una cita en fechas pasadas");
        }

        List<LocalTime> allBloquesHorario = new ArrayList<>();
        LocalTime inicio = LocalTime.of(9,0);
        LocalTime termino = LocalTime.of(19,0);

        while(!inicio.equals(termino)) {
            allBloquesHorario.add(inicio);
            inicio = inicio.plusMinutes(30);
        }

        List<LocalTime> horasOcupadas = repository.findAllHorasByMedicoIdAndFechaCita(medicoId, fecha);
        allBloquesHorario.removeAll(horasOcupadas);
        return allBloquesHorario;
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

        validarHorarioCita(dto.horaCita());
        validarChoqueHorario(medico.getId(), paciente.getId(), dto.fechaCita(), dto.horaCita());

        // Paciente 2 citas con el mismo medico, mismo día - Paciente, Medico y Cita
        if (repository.existsByPacienteIdAndMedicoIdAndFechaCita(
                paciente.getId(), medico.getId(), dto.fechaCita())
        ) {
            throw new ValidacionNegocioException("El paciente ya tiene una cita agendada con el médico para tal día");
        }

        // Validación para atenciones pasadas la fecha actual
        if (dto.fechaCita().isBefore(LocalDate.now())) {
            throw new ValidacionNegocioException("No se puede agendar una cita en fechas pasadas");
        }

        CitaMedica citaMedica = mapper.toCitaMedica(dto, medico, paciente);
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


    // Validaciones de lógica de negocio
    private void validarHorarioCita(LocalTime hora) {
        LocalTime horaInicio = LocalTime.of(9, 0);
        LocalTime horaTermino = LocalTime.of(19, 0);

        if (hora.isBefore(horaInicio) || hora.isAfter(horaTermino)) {
            throw new ValidacionNegocioException("El horario de citas es entre 9 y 19hrs");
        }

        if (hora.getMinute() != 0 && hora.getMinute() != 30) {
            throw new ValidacionNegocioException("Minutos inválidos. Las citas son en bloques de 30min");
        }
    }

    private void validarChoqueHorario(
            Long medicoId, Long pacienteId, LocalDate fecha, LocalTime hora
    ) {
        if (repository.existsByMedicoIdAndFechaCitaAndHoraCita(medicoId, fecha, hora)) {
            throw new ValidacionNegocioException("Este bloque de horario ya está reservado para el médico seleccionado");
        }

        if (repository.existsByPacienteIdAndFechaCitaAndHoraCita(pacienteId, fecha, hora)) {
            throw new ValidacionNegocioException("El paciente ya tiene una cita agendada para este bloque");
        }
    }
}
