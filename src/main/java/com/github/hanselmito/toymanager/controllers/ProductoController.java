package com.github.hanselmito.toymanager.controllers;

import com.github.hanselmito.toymanager.model.Producto;
import com.github.hanselmito.toymanager.model.Usuario;
import com.github.hanselmito.toymanager.services.ProductoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto, @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServices.crearProducto(producto, usuario));
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable String sku) {
        return ResponseEntity.ok(productoServices.obtenerProductoPorId(sku));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable String sku, @RequestBody Producto producto, @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(productoServices.actualizarProducto(sku, producto, usuario));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String sku) {
        productoServices.eliminarProducto(sku);
        return ResponseEntity.noContent().build();
    }
}