package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.exception.ResourceNotFoundException;
import com.github.hanselmito.toymanager.model.Producto;
import com.github.hanselmito.toymanager.model.ProductosProveedore;
import com.github.hanselmito.toymanager.model.ProductosProveedoreId;
import com.github.hanselmito.toymanager.model.Proveedore;
import com.github.hanselmito.toymanager.repositories.ProductoRepository;
import com.github.hanselmito.toymanager.repositories.ProductosProveedoreRepository;
import com.github.hanselmito.toymanager.repositories.ProveedoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosProveedoreServices {

    @Autowired
    private ProductosProveedoreRepository productosProveedoreRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedoreRepository proveedoreRepository;

    public ProductosProveedore crearProductosProveedore(ProductosProveedore productosProveedore) {
        if (productosProveedore.getId() == null || productosProveedore.getId().getProductoSku() == null || productosProveedore.getId().getProveedorCif() == null) {
            throw new IllegalArgumentException("El ID compuesto debe contener productoSku y proveedorCif.");
        }

        // Verificar existencia de producto y proveedor
        Producto producto = productoRepository.findById(productosProveedore.getId().getProductoSku())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con SKU: ", productosProveedore.getId().getProductoSku()));

        Proveedore proveedor = proveedoreRepository.findById(productosProveedore.getId().getProveedorCif())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con CIF: ", productosProveedore.getId().getProveedorCif()));

        // Configurar relaciones
        productosProveedore.setProductoSku(producto);
        productosProveedore.setProveedorCif(proveedor);

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