package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("""
    SELECT c FROM Categoria c
    WHERE c.nombre LIKE %:nombre%
    """)
    List<Categoria> findCategoriasPorNombre(@Param("nombre") String nombre);

    @Query("""
    SELECT c FROM Categoria c
    WHERE c.categoriaPadre.id = :idCategoriaPadre
    """)
    List<Categoria> findCategoriasPorCategoriaPadre(@Param("idCategoriaPadre") Integer idCategoriaPadre);

    @Query("""
    SELECT c FROM Categoria c
    """)
    List<Categoria> findTodasLasCategorias();
}