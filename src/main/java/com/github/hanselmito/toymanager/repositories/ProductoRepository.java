package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, String> {

    @Query("""
    SELECT p FROM Producto p
    WHERE p.stock > 0 AND p.precioVenta BETWEEN :precioMin AND :precioMax
    """)
    List<Producto> findProductosDisponiblesPorRangoDePrecio(
            @Param("precioMin") BigDecimal precioMin,
            @Param("precioMax") BigDecimal precioMax
    );

    @Query("""
SELECT p FROM Producto p
WHERE p.nombre LIKE %:nombre% OR p.sku LIKE %:nombre%
""")
    List<Producto> findProductosPorNombreOSku(@Param("nombre") String nombre);

    @Query("""
SELECT p FROM Producto p
""")
    List<Producto> findTodosLosProductos();

    @Query("""
SELECT p FROM Producto p
WHERE p.stock >= 1
""")
    List<Producto> findProductosConStock();
    @Query("""
    SELECT p FROM Producto p
    JOIN p.categorias c
    WHERE c.nombre = :nombreCategoria
    """)
    List<Producto> findProductosPorCategoria(@Param("nombreCategoria") String nombreCategoria);

    @Query("""
    SELECT p FROM Producto p
    WHERE p.stock = 0
    """)
    List<Producto> findProductosSinStock();
}