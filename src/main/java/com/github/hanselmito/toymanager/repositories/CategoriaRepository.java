package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByNombre(String nombre);

    Categoria findByCategoriaPadre(Categoria categoriaPadre);

    Categoria findByFechaCreacion(String fechaCreacion);

    Categoria findByCateggoriasUsuarios(String categgoriasUsuarios);

    Categoria findByCategorias(String categorias);

    Categoria findByProductos(String productos);
}
