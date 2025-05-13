package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Proveedore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedoRepository extends JpaRepository<Proveedore, Integer> {
    Proveedore findByNombre(String nombre);
}