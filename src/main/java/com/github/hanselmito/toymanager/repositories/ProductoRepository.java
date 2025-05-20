package com.github.hanselmito.toymanager.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.hanselmito.toymanager.model.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, String> {
    @Query("SELECT p FROM Producto p JOIN p.productosProveedores pp WHERE pp.proveedorCif.cif = :proveedorId")
    List<Producto> findByProveedorId(@Param("proveedorId") String proveedorId);
}