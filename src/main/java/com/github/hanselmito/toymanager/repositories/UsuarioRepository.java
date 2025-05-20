package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByEmail(String email);
    Usuario findByNombre(String username);
}