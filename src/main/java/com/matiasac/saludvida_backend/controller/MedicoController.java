package com.matiasac.saludvida_backend.controller;

import com.matiasac.saludvida_backend.config.validation.ValidationGroups;
import com.matiasac.saludvida_backend.model.dto.request.MedicoCreateDTO;
import com.matiasac.saludvida_backend.model.dto.request.MedicoUpdateDTO;
import com.matiasac.saludvida_backend.model.dto.response.MedicoResponseDTO;
import com.matiasac.saludvida_backend.service.implementation.MedicoServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medico")
@Validated
public class MedicoController {
    private final MedicoServiceImpl service;

    public MedicoController(MedicoServiceImpl service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> list() {
        List<MedicoResponseDTO> medicos = service.findAll();

        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(medicos);
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> create(
            @Validated(ValidationGroups.Orden.class) @RequestBody MedicoCreateDTO medicoDto
    ) {
       MedicoResponseDTO response = service.create(medicoDto);
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> update(
            @Valid @RequestBody MedicoUpdateDTO medicoUpdateDto,
            @PathVariable @Min(
                    value = 1,
                    message = "El ID debe ser mayor a 0."
            ) Long id
    ) {
        MedicoResponseDTO response = service.update(medicoUpdateDto, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @Min(
                    value = 1,
                    message = "El ID debe ser mayor a 0."
            ) Long id
    ) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
