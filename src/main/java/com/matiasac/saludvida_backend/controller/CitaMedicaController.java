package com.matiasac.saludvida_backend.controller;

import com.matiasac.saludvida_backend.model.dto.request.CitaMedicaRequestDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaDetalleResponseDTO;
import com.matiasac.saludvida_backend.model.dto.response.CitaMedicaListDTO;
import com.matiasac.saludvida_backend.service.ICitaMedicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cita-medica")
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

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedicaDetalleResponseDTO> details(
            @PathVariable Long id
    ) {
        CitaMedicaDetalleResponseDTO response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<CitaMedicaDetalleResponseDTO> create(
            @RequestBody CitaMedicaRequestDTO dto
    ) {
        CitaMedicaDetalleResponseDTO response = service.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedicaDetalleResponseDTO> update(
            @RequestBody CitaMedicaRequestDTO dto,
            @PathVariable Long id
    ) {
        CitaMedicaDetalleResponseDTO response = service.update(dto, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
