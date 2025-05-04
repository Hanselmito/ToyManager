package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Proveedore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedoRepository extends JpaRepository<Proveedore, Integer> {
    Proveedore findByNombre(String nombre);

    Proveedore findByRuc(String ruc);

    Proveedore findByTelefono(String telefono);

    Proveedore findByEmail(String email);

    Proveedore findByDireccion(String direccion);

    Proveedore findByFechaCreacion(String fechaCreacion);

    Proveedore findByUltimaActualizacion(String ultimaActualizacion);
}
