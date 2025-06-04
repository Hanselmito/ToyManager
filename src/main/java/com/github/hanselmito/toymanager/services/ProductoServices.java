package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.exception.ResourceNotFoundException;
import com.github.hanselmito.toymanager.model.*;
import com.github.hanselmito.toymanager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductoServices {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProveedoreRepository proveedoreRepository;

    @Autowired
    private ProductosUsuarioRepository productosUsuarioRepository;

    @Autowired
    private ProductosCategoriaRepository productosCategoriaRepository;

    @Autowired
    private ProductosProveedoreRepository productosProveedoreRepository;

    /**
     * Guarda un nuevo producto en la base de datos.
     */
    public Producto crearProducto(Producto producto, Integer usuarioNif) {
        Usuario usuario = usuarioRepository.findById(usuarioNif)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con NIF: ", usuarioNif));

        // Verificar si las categorías existen
        if (producto.getCategorias() != null && !producto.getCategorias().isEmpty()) {
            Set<Categoria> categoriasValidas = new LinkedHashSet<>();
            for (Categoria categoria : producto.getCategorias()) {
                categoriaRepository.findById(categoria.getId()).ifPresent(categoriasValidas::add);
            }
            producto.setCategorias(categoriasValidas); // Asignar solo las categorías válidas
        }

        // Guardar el producto
        Producto productoGuardado = productoRepository.save(producto);

        // Crear relaciones en ProductosCategoria
        if (producto.getCategorias() != null) {
            for (Categoria categoria : producto.getCategorias()) {
                ProductosCategoria productosCategoria = new ProductosCategoria();
                productosCategoria.setProductosSku(productoGuardado);
                productosCategoria.setCategorias(categoria);
                productosCategoria.setId(new ProductosCategoriaId(productoGuardado.getSku(), categoria.getId()));
                productosCategoriaRepository.save(productosCategoria);
            }
        }

        // Crear relación en ProductosUsuario
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

        productosUsuarioRepository.save(productosUsuario);

        return productoGuardado;
    }

    public Producto actualizarProducto(String sku, Set<Categoria> categorias) {
        Producto producto = productoRepository.findById(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con SKU: ", sku));

        // Actualizar categorías del producto
        producto.setCategorias(categorias);
        Producto productoActualizado = productoRepository.save(producto);

        // Actualizar relaciones en ProductosCategoria
        for (Categoria categoria : categorias) {
            ProductosCategoria productosCategoria = new ProductosCategoria();
            productosCategoria.setProductosSku(productoActualizado);
            productosCategoria.setCategorias(categoriaRepository.findById(categoria.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: ", categoria.getId())));
            productosCategoriaRepository.save(productosCategoria);
        }

        return productoActualizado;
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