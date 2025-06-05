package com.github.hanselmito.toymanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hanselmito.toymanager.model.Categoria;
import com.github.hanselmito.toymanager.model.Producto;
import com.github.hanselmito.toymanager.model.ProductosProveedore;
import com.github.hanselmito.toymanager.services.ProductoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    /**
     * Obtiene todos los productos disponibles.
     *
     * @return Lista de productos.
     */
    @CrossOrigin
    @PostMapping(value = "/crear", consumes = "multipart/form-data")
    public ResponseEntity<?> crearProducto(
            @RequestParam("producto") String productoJson,
            @RequestParam("usuarioNif") Integer usuarioNif,
            @RequestParam("imagen") MultipartFile imagen) {
        try {
            Producto producto = new ObjectMapper().readValue(productoJson, Producto.class);
            producto.setImagen(imagen.getBytes());

            if (producto.getStock() < 0) {
                return new ResponseEntity<>("El stock no puede ser negativo.", HttpStatus.BAD_REQUEST);
            }

            Producto nuevoProducto = productoServices.crearProducto(producto, usuarioNif);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PutMapping("/actualizar/{sku}")
    public ResponseEntity<?> actualizarProducto(
            @PathVariable String sku,
            @RequestBody Producto productoActualizado) {
        try {
            Producto producto = productoServices.obtenerProductoPorSku(sku);
            if (producto == null) {
                return new ResponseEntity<>("Producto no encontrado con SKU: " + sku, HttpStatus.NOT_FOUND);
            }

            // Actualizar los datos del producto
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setDescripcionCorta(productoActualizado.getDescripcionCorta());
            producto.setPrecioVenta(productoActualizado.getPrecioVenta());
            producto.setStock(productoActualizado.getStock());
            producto.setCategorias(productoActualizado.getCategorias());

            Producto productoActualizadoFinal = productoServices.actualizarProducto(sku, producto.getCategorias());
            return new ResponseEntity<>(productoActualizadoFinal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    @GetMapping("/porSku/{sku}")
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