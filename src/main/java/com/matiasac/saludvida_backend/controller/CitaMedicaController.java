package com.matiasac.saludvida_backend.controller;

import com.matiasac.saludvida_backend.model.dto.request.CitaMedicaRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaDetalleResponseDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaListDTO;
import com.matiasac.saludvida_backend.service.ICitaMedicaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/cita-medica")
@Validated
public class CitaMedicaController {
    private final ICitaMedicaService service;

    public CitaMedicaController(ICitaMedicaService citaMedicaService) {
        this.service = citaMedicaService;
    }


    @GetMapping
    public ResponseEntity<List<CitaMedicaListDTO>> list() {
        List<CitaMedicaListDTO> citasMedicas = service.findAll();

        if (citasMedicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(citasMedicas);
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<LocalTime>> listHorariosDisponiblesByFechaAndMedico(
            @RequestParam @NotNull @Min(value = 0, message = "El ID debe ser mayor a 0") Long medicoId,
            @RequestParam @NotNull @FutureOrPresent(message = "No se permiten fechas pasadas")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fecha
    ) {
        List<LocalTime> horariosDisponibles = service.findHorariosDisponiblesFecha(medicoId, fecha);

        if (horariosDisponibles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(horariosDisponibles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedicaDetalleResponseDTO> details(
            @PathVariable @Min(value = 0, message = "El ID no puede ser menor a 0") Long id
    ) {
        CitaMedicaDetalleResponseDTO response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<CitaMedicaDetalleResponseDTO> create(
            @Valid @RequestBody CitaMedicaRequestDTO dto
    ) {
        CitaMedicaDetalleResponseDTO response = service.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedicaDetalleResponseDTO> update(
            @RequestBody CitaMedicaRequestDTO dto,
            @PathVariable @Min(value = 0, message = "El ID debe ser mayor a 0") Long id
    ) {
        CitaMedicaDetalleResponseDTO response = service.update(dto, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @Min(value = 0, message = "El ID debe ser mayor a 0") Long id
    ) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
