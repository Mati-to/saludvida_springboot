package com.matiasac.saludvida_backend.controller;

import com.matiasac.saludvida_backend.model.dto.EspecialidadDTO;
import com.matiasac.saludvida_backend.model.dto.response.EspecialidadResponseDTO;
import com.matiasac.saludvida_backend.service.implementation.EspecialidadServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/especialidad")
@Validated
public class EspecialidadController {
    private final EspecialidadServiceImpl service;

    public EspecialidadController(EspecialidadServiceImpl service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<EspecialidadResponseDTO>> list() {
        List<EspecialidadResponseDTO> especialidades = service.findAll();

        if (especialidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity
                .ok()
                .body(especialidades);
    }

    @PostMapping
    public ResponseEntity<EspecialidadResponseDTO> create(
            @Valid @RequestBody EspecialidadDTO especialidadDto
    ) {
        EspecialidadResponseDTO response = service.create(especialidadDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadResponseDTO> update(
            @Valid @RequestBody EspecialidadDTO especialidadDto,
            @PathVariable @Min(
                    value = 1,
                    message = "El ID debe ser mayor a 0.") Long id
    ) {
        EspecialidadResponseDTO response = service.update(especialidadDto, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @Min(
                    value = 1,
                    message = "El ID debe ser mayor a 0.") Long id
    ) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
