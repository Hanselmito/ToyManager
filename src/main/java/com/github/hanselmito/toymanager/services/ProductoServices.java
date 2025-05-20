package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.exception.ResourceNotFoundException;
import com.github.hanselmito.toymanager.model.*;
import com.github.hanselmito.toymanager.repositories.ProductoRepository;
import com.github.hanselmito.toymanager.repositories.ProductosUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductoServices {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductosUsuarioRepository productosUsuarioRepository;

    public Producto crearProducto(Producto producto, Usuario usuario) {
        Producto nuevoProducto = productoRepository.save(producto);
        registrarProductoUsuario(nuevoProducto, usuario);
        return nuevoProducto;
    }

    public Producto obtenerProductoPorId(String sku) {
        return productoRepository.findById(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con SKU: " + sku));
    }

    public Producto actualizarProducto(String sku, Producto productoActualizado, Usuario usuario) {
        Producto producto = obtenerProductoPorId(sku);
        producto.setNombre(productoActualizado.getNombre());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setDescripcionCorta(productoActualizado.getDescripcionCorta());
        producto.setPrecioVenta(productoActualizado.getPrecioVenta());
        producto.setStock(productoActualizado.getStock());
        producto.setImagen(productoActualizado.getImagen());
        Producto productoGuardado = productoRepository.save(producto);
        registrarProductoUsuario(productoGuardado, usuario);
        return productoGuardado;
    }

    public void eliminarProducto(String sku) {
        productoRepository.deleteById(sku);
    }

    private void registrarProductoUsuario(Producto producto, Usuario usuario) {
        ProductosUsuario productosUsuario = new ProductosUsuario();
        productosUsuario.setId(new ProductosUsuarioId(usuario.getId(), producto.getSku()));
        productosUsuario.setUsuarioNif(usuario);
        productosUsuario.setProductoSku(producto);
        productosUsuario.setNombreProducto(producto.getNombre());
        productosUsuario.setDescripcion(producto.getDescripcion());
        productosUsuario.setDescripcionCorta(producto.getDescripcionCorta());
        productosUsuario.setPrecioVenta(producto.getPrecioVenta());
        productosUsuario.setStock(producto.getStock());
        productosUsuario.setImagen(producto.getImagen());
        productosUsuario.setFechaCreacion(Instant.now());
        productosUsuarioRepository.save(productosUsuario);
    }
}