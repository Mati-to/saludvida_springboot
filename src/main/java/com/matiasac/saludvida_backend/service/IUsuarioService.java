package com.matiasac.saludvida_backend.service;

import com.matiasac.saludvida_backend.model.dto.request.UsuarioCreateDTO;
import com.matiasac.saludvida_backend.model.dto.request.UsuarioUpdateDTO;
import com.matiasac.saludvida_backend.model.dto.response.UsuarioResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUsuarioService extends UserDetailsService {

    List<UsuarioResponseDTO> findAll();
    UsuarioResponseDTO create(UsuarioCreateDTO dto);
    UsuarioResponseDTO update(UsuarioUpdateDTO dto, Long id);
    void deleteById(Long id);
}
