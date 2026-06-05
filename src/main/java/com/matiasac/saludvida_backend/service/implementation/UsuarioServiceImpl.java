package com.matiasac.saludvida_backend.service.implementation;

import com.matiasac.saludvida_backend.exception.NotFoundException;
import com.matiasac.saludvida_backend.exception.RecursoDuplicadoException;
import com.matiasac.saludvida_backend.model.dto.request.UsuarioCreateDTO;
import com.matiasac.saludvida_backend.model.dto.request.UsuarioUpdateDTO;
import com.matiasac.saludvida_backend.model.dto.response.UsuarioResponseDTO;
import com.matiasac.saludvida_backend.model.entity.Usuario;
import com.matiasac.saludvida_backend.model.mapper.UsuarioMapper;
import com.matiasac.saludvida_backend.repository.IUsuarioRepository;
import com.matiasac.saludvida_backend.service.IUsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UsuarioServiceImpl implements IUsuarioService {
    private final IUsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioServiceImpl(IUsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = repository.findAll();

        return usuarios.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UsuarioResponseDTO create(UsuarioCreateDTO dto) {
        if(repository.existsByUsername(dto.username())) {
            throw new RecursoDuplicadoException("Ya existe un usuario con ese username");
        }

        Usuario usuario = mapper.toUsuario(dto);
        repository.save(usuario);
        return mapper.toDto(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioUpdateDTO dtoUpdate, Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario", id));

        mapper.toUpdateUsuario(usuario, dtoUpdate);
        return mapper.toDto(usuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario", id));
        repository.deleteById(usuario.getId());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
