package com.matiasac.saludvida_backend.controller;

import com.matiasac.saludvida_backend.model.dto.EspecialidadDTO;
import com.matiasac.saludvida_backend.model.dto.response.EspecialidadResponseDTO;
import com.matiasac.saludvida_backend.service.implementation.EspecialidadServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/especialidad")
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
            @RequestBody EspecialidadDTO especialidadDto
    ) {
        EspecialidadResponseDTO response = service.create(especialidadDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadResponseDTO> update(
            @RequestBody EspecialidadDTO especialidadDto,
            @PathVariable Long id
    ) {
        EspecialidadResponseDTO response = service.update(especialidadDto, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
