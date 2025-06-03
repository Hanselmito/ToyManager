package com.github.hanselmito.toymanager.controllers;

import com.github.hanselmito.toymanager.model.Producto;
import com.github.hanselmito.toymanager.services.ProductoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    /**
     * Crea un nuevo producto.
     *
     * @param producto Datos del producto a crear.
     * @return Producto creado o mensaje de error.
     */
    @CrossOrigin
    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto, @RequestParam Integer usuarioNif) {
        try {
            if (producto.getStock() < 0) {
                return new ResponseEntity<>("El stock no puede ser negativo.", HttpStatus.BAD_REQUEST);
            }

            Producto nuevoProducto = productoServices.crearProducto(producto, usuarioNif);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene todos los productos disponibles.
     *
     * @return Lista de productos.
     */
    @CrossOrigin
    @GetMapping("/todos")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoServices.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    /**
     * Obtiene todos los productos con stock disponible.
     *
     * @return Lista de productos con stock.
     */
    @CrossOrigin
    @GetMapping("/conStock")
    public ResponseEntity<List<Producto>> obtenerProductosConStock() {
        List<Producto> productosConStock = productoServices.obtenerProductosConStock();
        return new ResponseEntity<>(productosConStock, HttpStatus.OK);
    }

    /**
     * Busca productos por nombre o SKU.
     *
     * @param nombreOSku Nombre o SKU del producto.
     * @return Lista de productos encontrados.
     */
    @CrossOrigin
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProductosPorNombreOSku(@RequestParam String nombreOSku) {
        List<Producto> productos = productoServices.buscarProductosPorNombreOSku(nombreOSku);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    /**
     * Obtiene un producto por su SKU.
     *
     * @param sku SKU del producto.
     * @return Producto encontrado o mensaje de error.
     */
    @CrossOrigin
    @GetMapping("/{sku}")
    public ResponseEntity<Producto> obtenerProductoPorSku(@PathVariable String sku) {
        Producto producto = productoServices.obtenerProductoPorSku(sku);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un producto por su SKU.
     *
     * @param sku SKU del producto.
     * @return Código de estado según resultado de la operación.
     */
    @CrossOrigin
    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String sku) {
        boolean eliminado = productoServices.eliminarProducto(sku);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}