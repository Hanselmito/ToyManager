package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Proveedore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedoreRepository extends JpaRepository<Proveedore, String> {
    Proveedore findByNombre(String nombre);
}