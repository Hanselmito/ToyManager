package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {
    Categoria findByNombre(String nombre);
}