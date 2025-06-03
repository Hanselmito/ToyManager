package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.exception.ResourceNotFoundException;
import com.github.hanselmito.toymanager.model.Producto;
import com.github.hanselmito.toymanager.model.ProductosUsuario;
import com.github.hanselmito.toymanager.model.ProductosUsuarioId;
import com.github.hanselmito.toymanager.model.Usuario;
import com.github.hanselmito.toymanager.repositories.ProductoRepository;
import com.github.hanselmito.toymanager.repositories.ProductosUsuarioRepository;
import com.github.hanselmito.toymanager.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductoServices {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductosUsuarioRepository productosUsuarioRepository;

    /**
     * Guarda un nuevo producto en la base de datos.
     */
    public Producto crearProducto(Producto producto, Integer usuarioNif) {
        Usuario usuario = usuarioRepository.findById(usuarioNif)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con NIF: ", usuarioNif));

        // Guardar el producto
        Producto productoGuardado = productoRepository.save(producto);

        // Crear la relación en ProductosUsuario
        ProductosUsuario productosUsuario = new ProductosUsuario();
        productosUsuario.setId(new ProductosUsuarioId(usuarioNif, productoGuardado.getSku()));
        productosUsuario.setUsuarioNif(usuario);
        productosUsuario.setProductoSku(productoGuardado);
        productosUsuario.setNombreProducto(productoGuardado.getNombre());
        productosUsuario.setDescripcion(productoGuardado.getDescripcion());
        productosUsuario.setDescripcionCorta(productoGuardado.getDescripcionCorta());
        productosUsuario.setPrecioVenta(productoGuardado.getPrecioVenta());
        productosUsuario.setStock(productoGuardado.getStock());
        productosUsuario.setImagen(productoGuardado.getImagen());
        productosUsuario.setFechaCreacion(Instant.now());

        // Guardar la relación
        productosUsuarioRepository.save(productosUsuario);

        return productoGuardado;
    }

    /**
     * Busca un producto por su SKU.
     */
    public Producto obtenerProductoPorSku(String sku) {
        return productoRepository.findById(sku).orElse(null);
    }

    /**
     * Elimina un producto por su SKU.
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminarProducto(String sku) {
        if (productoRepository.existsById(sku)) {
            productoRepository.deleteById(sku);
            return true;
        }
        return false;
    }

    /**
     * Obtiene todos los productos disponibles.
     */
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findTodosLosProductos();
    }

    /**
     * Obtiene todos los productos con stock disponible.
     */
    public List<Producto> obtenerProductosConStock() {
        return productoRepository.findProductosConStock();
    }

    /**
     * Busca productos por nombre o SKU.
     */
    public List<Producto> buscarProductosPorNombreOSku(String nombreOSku) {
        return productoRepository.findProductosPorNombreOSku(nombreOSku);
    }

    /**
     * Obtiene productos por categoría.
     */
    public List<Producto> obtenerProductosPorCategoria(String nombreCategoria) {
        return productoRepository.findProductosPorCategoria(nombreCategoria);
    }

    /**
     * Obtiene productos sin stock.
     */
    public List<Producto> obtenerProductosSinStock() {
        return productoRepository.findProductosSinStock();
    }
}