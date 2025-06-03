package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Proveedore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProveedoreRepository extends JpaRepository<Proveedore, String> {

    @Query("""
    SELECT p FROM Proveedore p
    WHERE p.nombre LIKE %:nombre%
    """)
    List<Proveedore> findProveedoresPorNombre(@Param("nombre") String nombre);
}