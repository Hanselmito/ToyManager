package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.model.Categoria;
import com.github.hanselmito.toymanager.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Obtiene todas las categorías.
     */
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findTodasLasCategorias();
    }

    /**
     * Busca categorías por nombre.
     */
    public List<Categoria> buscarCategoriasPorNombre(String nombre) {
        return categoriaRepository.findCategoriasPorNombre(nombre);
    }

    /**
     * Busca categorías por categoría padre.
     */
    public List<Categoria> buscarCategoriasPorCategoriaPadre(Integer idCategoriaPadre) {
        return categoriaRepository.findCategoriasPorCategoriaPadre(idCategoriaPadre);
    }

    /**
     * Guarda una nueva categoría.
     */
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    /**
     * Elimina una categoría por su ID.
     */
    public void eliminarCategoriaPorId(Integer id) {
        categoriaRepository.deleteById(id);
    }
}