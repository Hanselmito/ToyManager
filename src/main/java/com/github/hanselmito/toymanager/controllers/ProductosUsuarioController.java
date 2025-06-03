package com.github.hanselmito.toymanager.controllers;

import com.github.hanselmito.toymanager.model.ProductosUsuario;
import com.github.hanselmito.toymanager.services.ProductosUsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-usuarios")
public class ProductosUsuarioController {

    @Autowired
    private ProductosUsuarioServices productosUsuarioServices;

    /**
     * Guarda un nuevo producto asociado a un usuario.
     */
    @CrossOrigin
    @PostMapping("/guardar")
    public ResponseEntity<ProductosUsuario> guardarProductoUsuario(@RequestBody ProductosUsuario productosUsuario) {
        ProductosUsuario nuevoProductoUsuario = productosUsuarioServices.guardarProductoUsuario(productosUsuario);
        return new ResponseEntity<>(nuevoProductoUsuario, HttpStatus.CREATED);
    }

    /**
     * Obtiene todos los productos asociados a un usuario.
     */
    @CrossOrigin
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ProductosUsuario>> obtenerProductosPorUsuario(@PathVariable Integer idUsuario) {
        List<ProductosUsuario> productosUsuario = productosUsuarioServices.obtenerProductosPorUsuario(idUsuario);
        return new ResponseEntity<>(productosUsuario, HttpStatus.OK);
    }

    /**
     * Obtiene un producto específico asociado a un usuario por su SKU.
     */
    @CrossOrigin
    @GetMapping("/usuario/{idUsuario}/producto/{sku}")
    public ResponseEntity<ProductosUsuario> obtenerProductoUsuarioPorUsuarioYSku(@PathVariable Integer idUsuario, @PathVariable String sku) {
        ProductosUsuario productoUsuario = productosUsuarioServices.obtenerProductoUsuarioPorUsuarioYSku(idUsuario, sku);
        if (productoUsuario != null) {
            return ResponseEntity.ok(productoUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un producto asociado a un usuario.
     */
    @CrossOrigin
    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarProductoUsuario(@RequestBody ProductosUsuario productosUsuario) {
        boolean eliminado = productosUsuarioServices.eliminarProductoUsuario(productosUsuario.getId());
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}