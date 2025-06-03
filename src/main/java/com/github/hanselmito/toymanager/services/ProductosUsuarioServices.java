package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.model.ProductosUsuario;
import com.github.hanselmito.toymanager.model.ProductosUsuarioId;
import com.github.hanselmito.toymanager.repositories.ProductosUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosUsuarioServices {

    @Autowired
    private ProductosUsuarioRepository productosUsuarioRepository;

    /**
     * Guarda un nuevo producto asociado a un usuario.
     */
    public ProductosUsuario guardarProductoUsuario(ProductosUsuario productosUsuario) {
        return productosUsuarioRepository.save(productosUsuario);
    }

    /**
     * Obtiene todos los productos asociados a un usuario.
     */
    public List<ProductosUsuario> obtenerProductosPorUsuario(Integer idUsuario) {
        return productosUsuarioRepository.findProductosByUsuario(idUsuario);
    }

    /**
     * Obtiene un producto específico asociado a un usuario por su SKU.
     */
    public ProductosUsuario obtenerProductoUsuarioPorUsuarioYSku(Integer idUsuario, String sku) {
        return productosUsuarioRepository.findProductoUsuarioByUsuarioAndSku(idUsuario, sku);
    }

    /**
     * Elimina un producto asociado a un usuario.
     */
    public boolean eliminarProductoUsuario(ProductosUsuarioId id) {
        if (productosUsuarioRepository.existsById(id)) {
            productosUsuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}