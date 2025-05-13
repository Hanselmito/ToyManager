package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.model.Categoria;
import com.github.hanselmito.toymanager.model.Producto;
import com.github.hanselmito.toymanager.repositories.CategoriaRepository;
import com.github.hanselmito.toymanager.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public void asignarCategorias(Producto producto, Set<Categoria> categorias) {
        Set<Categoria> categoriasFinales = new LinkedHashSet<>(categorias);

        for (Categoria categoria : categorias) {
            while (categoria.getCategoriaPadre() != null) {
                categoriasFinales.add(categoria.getCategoriaPadre());
                categoria = categoria.getCategoriaPadre();
            }
        }

        producto.setCategorias(categoriasFinales);
        productoRepository.save(producto);
    }

    public List<Categoria> findAll() {
        return null;
    }

    public Categoria findById(Integer id) {
        return null;
    }

    public Categoria save(Categoria categoria) {
        return null;
    }

    public void deleteById(Integer id) {
    }
}
