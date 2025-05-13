package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.model.Producto;
import com.github.hanselmito.toymanager.model.ProductosUsuario;
import com.github.hanselmito.toymanager.model.ProductosUsuarioId;
import com.github.hanselmito.toymanager.model.Usuario;
import com.github.hanselmito.toymanager.repositories.ProductoRepository;
import com.github.hanselmito.toymanager.repositories.ProductosUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductoServices {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductosUsuarioRepository productosUsuarioRepository;

    public Producto actualizarProducto(Producto producto, Usuario usuario) {
        Producto productoActualizado = productoRepository.save(producto);

        ProductosUsuario productosUsuario = new ProductosUsuario();
        productosUsuario.setId(new ProductosUsuarioId(usuario.getId(), producto.getId()));
        productosUsuario.setUsuario(usuario);
        productosUsuario.setProductos(producto);
        productosUsuario.setNombreProducto(producto.getNombre());
        productosUsuario.setDescripcion(producto.getDescripcion());
        productosUsuario.setDescripcionCorta(producto.getDescripcionCorta());
        productosUsuario.setPrecio(producto.getPrecio());
        productosUsuario.setCantidad(producto.getCantidad());
        productosUsuario.setImagen(producto.getImagen());
        productosUsuario.setFechaCreacion(Instant.now());

        productosUsuarioRepository.save(productosUsuario);

        return productoActualizado;
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void eliminarProducto(Integer id) {
    }
}
