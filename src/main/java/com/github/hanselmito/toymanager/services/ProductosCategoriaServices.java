package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.model.ProductosCategoria;
import com.github.hanselmito.toymanager.model.ProductosCategoriaId;
import com.github.hanselmito.toymanager.repositories.ProductosCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosCategoriaServices {

    @Autowired
    private ProductosCategoriaRepository productosCategoriaRepository;

    /**
     * Guarda una nueva relación entre producto y categoría.
     */
    public ProductosCategoria guardarProductosCategoria(ProductosCategoria productosCategoria) {
        return productosCategoriaRepository.save(productosCategoria);
    }

    /**
     * Obtiene productos por categoría.
     */
    public List<ProductosCategoria> obtenerProductosPorCategoria(Integer idCategoria) {
        return productosCategoriaRepository.findProductosPorCategoria(idCategoria);
    }

    /**
     * Obtiene categorías por producto.
     */
    public List<ProductosCategoria> obtenerCategoriasPorProducto(String skuProducto) {
        return productosCategoriaRepository.findCategoriasPorProducto(skuProducto);
    }

    /**
     * Elimina una relación entre producto y categoría.
     */
    public boolean eliminarProductosCategoria(ProductosCategoriaId id) {
        if (productosCategoriaRepository.existsById(id)) {
            productosCategoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}