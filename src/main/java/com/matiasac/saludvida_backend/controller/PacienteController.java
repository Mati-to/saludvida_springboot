package com.matiasac.saludvida_backend.controller;

import com.matiasac.saludvida_backend.model.dto.request.PacienteRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.PacienteResponseDTO;
import com.matiasac.saludvida_backend.service.implementation.PacienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {
    private final PacienteServiceImpl service;

    public PacienteController(PacienteServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> list() {
        List<PacienteResponseDTO> pacientes = service.findAll();

        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(pacientes);
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> create(
            @RequestBody PacienteRequestDTO pacienteDto
    ) {
        PacienteResponseDTO response = service.create(pacienteDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> update(
            @RequestBody PacienteRequestDTO pacienteUpdateDto,
            @PathVariable Long id
    ) {
        PacienteResponseDTO response = service.update(pacienteUpdateDto, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
