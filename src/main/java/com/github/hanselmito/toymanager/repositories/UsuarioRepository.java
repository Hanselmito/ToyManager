package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsUsuarioByEmailOrNombre(String email, String username);

    @Query("SELECT u FROM Usuario u WHERE TRIM(u.email) = :email")
    Usuario findByEmail(@Param("email") String email);
    Usuario findByNombre(String username);
    List<Usuario> findAll();
}