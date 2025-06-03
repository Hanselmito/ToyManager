package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.exception.ResourceNotFoundException;
import com.github.hanselmito.toymanager.model.ProductosProveedore;
import com.github.hanselmito.toymanager.model.ProductosProveedoreId;
import com.github.hanselmito.toymanager.repositories.ProductosProveedoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosProveedoreServices {

    @Autowired
    private ProductosProveedoreRepository productosProveedoreRepository;

    public ProductosProveedore crearProductosProveedore(ProductosProveedore productosProveedore) {
        return productosProveedoreRepository.save(productosProveedore);
    }

    public ProductosProveedore obtenerProductosProveedorePorId(ProductosProveedoreId id) {
        return productosProveedoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductosProveedore", "ID: " + id));
    }

    public ProductosProveedore actualizarProductosProveedore(ProductosProveedoreId id, ProductosProveedore productosProveedoreActualizado) {
        ProductosProveedore productosProveedore = obtenerProductosProveedorePorId(id);
        productosProveedore.setPrecioCompra(productosProveedoreActualizado.getPrecioCompra());
        return productosProveedoreRepository.save(productosProveedore);
    }

    public void eliminarProductosProveedore(ProductosProveedoreId id) {
        productosProveedoreRepository.deleteById(id);
    }

    public List<ProductosProveedore> obtenerPorProductoSku(String sku) {
        return productosProveedoreRepository.findByProductoSku(sku);
    }

    public List<ProductosProveedore> obtenerPorProveedorCif(String cif) {
        return productosProveedoreRepository.findByProveedorCif(cif);
    }
}