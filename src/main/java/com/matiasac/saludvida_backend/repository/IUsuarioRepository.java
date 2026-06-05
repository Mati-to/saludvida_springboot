package com.matiasac.saludvida_backend.repository;

import com.matiasac.saludvida_backend.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
    boolean existsByUsername(String username);

}
