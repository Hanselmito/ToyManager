package com.github.hanselmito.toymanager.controllers;

import com.github.hanselmito.toymanager.model.ProductosCategoria;
import com.github.hanselmito.toymanager.services.ProductosCategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-categoria")
public class ProductosCategoriaController {

    @Autowired
    private ProductosCategoriaServices productosCategoriaServices;

    /**
     * Guarda una nueva relación entre producto y categoría.
     */
    @CrossOrigin
    @PostMapping("/guardar")
    public ResponseEntity<ProductosCategoria> guardarProductosCategoria(@RequestBody ProductosCategoria productosCategoria) {
        ProductosCategoria nuevaRelacion = productosCategoriaServices.guardarProductosCategoria(productosCategoria);
        return new ResponseEntity<>(nuevaRelacion, HttpStatus.CREATED);
    }

    /**
     * Obtiene productos por categoría.
     */
    @CrossOrigin
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductosCategoria>> obtenerProductosPorCategoria(@PathVariable Integer idCategoria) {
        List<ProductosCategoria> productos = productosCategoriaServices.obtenerProductosPorCategoria(idCategoria);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    /**
     * Obtiene categorías por producto.
     */
    @CrossOrigin
    @GetMapping("/producto/{skuProducto}")
    public ResponseEntity<List<ProductosCategoria>> obtenerCategoriasPorProducto(@PathVariable String skuProducto) {
        List<ProductosCategoria> categorias = productosCategoriaServices.obtenerCategoriasPorProducto(skuProducto);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    /**
     * Elimina una relación entre producto y categoría.
     */
    @CrossOrigin
    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarProductosCategoria(@RequestBody ProductosCategoria productosCategoria) {
        boolean eliminado = productosCategoriaServices.eliminarProductosCategoria(productosCategoria.getId());
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}