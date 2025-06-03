package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.ProductosUsuario;
import com.github.hanselmito.toymanager.model.ProductosUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductosUsuarioRepository extends JpaRepository<ProductosUsuario, ProductosUsuarioId> {

    @Query("""
    SELECT pu FROM ProductosUsuario pu
    WHERE pu.usuarioNif.id = :idUsuario
    """)
    List<ProductosUsuario> findProductosByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("""
    SELECT pu FROM ProductosUsuario pu
    WHERE pu.usuarioNif.id = :idUsuario AND pu.productoSku.sku = :sku
    """)
    ProductosUsuario findProductoUsuarioByUsuarioAndSku(@Param("idUsuario") Integer idUsuario, @Param("sku") String sku);
}