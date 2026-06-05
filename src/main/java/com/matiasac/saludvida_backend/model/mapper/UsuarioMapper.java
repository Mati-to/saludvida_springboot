package com.matiasac.saludvida_backend.model.mapper;

import com.matiasac.saludvida_backend.model.dto.request.UsuarioCreateDTO;
import com.matiasac.saludvida_backend.model.dto.request.UsuarioUpdateDTO;
import com.matiasac.saludvida_backend.model.dto.response.UsuarioResponseDTO;
import com.matiasac.saludvida_backend.model.entity.Usuario;

public class UsuarioMapper {

    public Usuario toUsuario(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario(
                dto.nombre(),
                dto.apellido(),
                dto.username(),
                dto.password()
        );
        usuario.setRol(dto.rol());
        return usuario;
    }

    public UsuarioResponseDTO toDto(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getNombreCompleto(),
                usuario.getRol()
        );
    }

    public void toUpdateUsuario(Usuario usuario, UsuarioUpdateDTO dto) {
        usuario.setNombre(dto.nombre());
        usuario.setApellido(dto.apellido());
        usuario.setPassword(dto.password());
    }
}
