package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);

    Usuario findByNombre(String nombre);

    Usuario findByContrasena(String contrasena);

    Usuario findByUltimoAcceso(String ultimoAcceso);
}
