package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Producto findByNombre(String nombre);

    Producto findByDescripcion(String descripcion);

    Producto findByPrecio(double precio);

    Producto findByCantidad(int cantidad);

    Producto findByCategoria(String categoria);

    Producto findByFechaCreacion(String fechaCreacion);

    Producto findByUltimaActualizacion(String ultimaActualizacion);
}
