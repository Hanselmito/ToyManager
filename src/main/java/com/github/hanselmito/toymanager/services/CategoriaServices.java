package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.model.Categoria;
import com.github.hanselmito.toymanager.repositories.CategoriaRepository;
import org.hibernate.Hibernate;
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
        List<Categoria> categorias = categoriaRepository.findCategoriasPorCategoriaPadre(idCategoriaPadre);
        categorias.forEach(categoria -> Hibernate.initialize(categoria.getCategoriaPadre()));
        return categorias;
    }

    /**
     * Guarda una nueva categoría.
     */
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria agregarCategoriaHija(Integer idCategoriaPadre, Categoria categoriaHija) {
        Categoria categoriaPadre = categoriaRepository.findById(idCategoriaPadre)
                .orElseThrow(() -> new IllegalArgumentException("La categoría padre con ID " + idCategoriaPadre + " no existe."));

        categoriaHija.setCategoriaPadre(categoriaPadre);
        return categoriaRepository.save(categoriaHija);
    }

    /**
     * Elimina una categoría por su ID.
     */
    public void eliminarCategoriaPorId(Integer id) {
        categoriaRepository.deleteById(id);
    }
}