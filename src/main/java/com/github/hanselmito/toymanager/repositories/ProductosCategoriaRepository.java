package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.ProductosCategoria;
import com.github.hanselmito.toymanager.model.ProductosCategoriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductosCategoriaRepository extends JpaRepository<ProductosCategoria, ProductosCategoriaId> {

    @Query("""
    SELECT pc FROM ProductosCategoria pc
    WHERE pc.categorias.id = :idCategoria
    """)
    List<ProductosCategoria> findProductosPorCategoria(@Param("idCategoria") Integer idCategoria);

    @Query("""
    SELECT pc FROM ProductosCategoria pc
    WHERE pc.productosSku.sku = :skuProducto
    """)
    List<ProductosCategoria> findCategoriasPorProducto(@Param("skuProducto") String skuProducto);
}