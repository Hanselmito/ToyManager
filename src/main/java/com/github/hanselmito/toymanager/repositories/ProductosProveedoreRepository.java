package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.ProductosProveedore;
import com.github.hanselmito.toymanager.model.ProductosProveedoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductosProveedoreRepository extends JpaRepository<ProductosProveedore, ProductosProveedoreId> {

    @Query("""
    SELECT pp FROM ProductosProveedore pp
    WHERE pp.productoSku.sku = :sku
    """)
    List<ProductosProveedore> findByProductoSku(@Param("sku") String sku);

    @Query("""
    SELECT pp FROM ProductosProveedore pp
    WHERE pp.proveedorCif.cif = :cif
    """)
    List<ProductosProveedore> findByProveedorCif(@Param("cif") String cif);

    @Query("SELECT pp FROM ProductosProveedore pp")
    List<ProductosProveedore> findProductosProveedoresAll();

}