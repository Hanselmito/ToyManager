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

    /**
     * Crea una nueva relación entre un producto y un proveedor.
     *
     * @param productosProveedore Objeto que contiene la relación a crear.
     * @return El objeto creado.
     */
    public ProductosProveedore crearProductosProveedore(ProductosProveedore productosProveedore) {
        if (productosProveedore.getId() == null || productosProveedore.getId().getProductoSku() == null || productosProveedore.getId().getProveedorCif() == null) {
            throw new IllegalArgumentException("El ID compuesto debe contener productoSku y proveedorCif.");
        }

        Producto producto = productoRepository.findById(productosProveedore.getId().getProductoSku())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con SKU: ", productosProveedore.getId().getProductoSku()));

        Proveedore proveedor = proveedoreRepository.findById(productosProveedore.getId().getProveedorCif())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con CIF: ", productosProveedore.getId().getProveedorCif()));

        productosProveedore.setProductoSku(producto);
        productosProveedore.setProveedorCif(proveedor);

        return productosProveedoreRepository.save(productosProveedore);
    }

    /**
     * Obtiene un producto proveedor por su ID compuesto.
     *
     * @param id ID compuesto del producto proveedor.
     * @return El objeto ProductosProveedore encontrado.
     */
    public ProductosProveedore obtenerProductosProveedorePorId(ProductosProveedoreId id) {
        return productosProveedoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductosProveedore", "ID: " + id));
    }

    /**
     * Actualiza un producto proveedor existente.
     *
     * @param id ID compuesto del producto proveedor a actualizar.
     * @param productosProveedoreActualizado Objeto con los nuevos datos.
     * @return El objeto actualizado.
     */
    public ProductosProveedore actualizarProductosProveedore(ProductosProveedoreId id, ProductosProveedore productosProveedoreActualizado) {
        ProductosProveedore productosProveedore = obtenerProductosProveedorePorId(id);
        productosProveedore.setPrecioCompra(productosProveedoreActualizado.getPrecioCompra());
        return productosProveedoreRepository.save(productosProveedore);
    }

    /**
     * Elimina un producto proveedor por su ID compuesto.
     *
     * @param id ID compuesto del producto proveedor a eliminar.
     */
    public void eliminarProductosProveedore(ProductosProveedoreId id) {
        productosProveedoreRepository.deleteById(id);
    }

    /**
     * Obtiene todos los productos proveedores asociados a un producto por su SKU.
     *
     * @param sku SKU del producto.
     * @return Lista de ProductosProveedore asociados al producto.
     */
    public List<ProductosProveedore> obtenerPorProductoSku(String sku) {
        return productosProveedoreRepository.findByProductoSku(sku);
    }

    /**
     * Obtiene todos los productos proveedores asociados a un proveedor por su CIF.
     *
     * @param cif CIF del proveedor.
     * @return Lista de ProductosProveedore asociados al proveedor.
     */
    public List<ProductosProveedore> obtenerPorProveedorCif(String cif) {
        return productosProveedoreRepository.findByProveedorCif(cif);
    }

    /**
     * Obtiene todos los productos proveedores.
     *
     * @return Lista de todos los ProductosProveedore.
     */
    public List<ProductosProveedore> obtenerTodosLosProductosProveedores() {
        return productosProveedoreRepository.findProductosProveedoresAll();
    }
}