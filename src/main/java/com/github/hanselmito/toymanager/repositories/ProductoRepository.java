package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoriasId(Integer categoriaId);
    List<Producto> findByProveedoresId(Integer proveedorId);
}