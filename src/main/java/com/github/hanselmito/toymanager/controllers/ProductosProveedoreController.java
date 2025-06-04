package com.github.hanselmito.toymanager.controllers;

import com.github.hanselmito.toymanager.model.ProductosProveedore;
import com.github.hanselmito.toymanager.model.ProductosProveedoreId;
import com.github.hanselmito.toymanager.services.ProductosProveedoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-proveedores")
public class ProductosProveedoreController {

    @Autowired
    private ProductosProveedoreServices productosProveedoreServices;

    @PostMapping("/crear")
    public ResponseEntity<ProductosProveedore> crearProductosProveedore(@RequestBody ProductosProveedore productosProveedore) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productosProveedoreServices.crearProductosProveedore(productosProveedore));
    }

    @GetMapping("/{productoSku}/{proveedorCif}")
    public ResponseEntity<ProductosProveedore> obtenerProductosProveedorePorId(@PathVariable String productoSku, @PathVariable String proveedorCif) {
        ProductosProveedoreId id = new ProductosProveedoreId();
        id.setProductoSku(productoSku);
        id.setProveedorCif(proveedorCif);
        return ResponseEntity.ok(productosProveedoreServices.obtenerProductosProveedorePorId(id));
    }

    @PutMapping("/{productoSku}/{proveedorCif}")
    public ResponseEntity<ProductosProveedore> actualizarProductosProveedore(@PathVariable String productoSku, @PathVariable String proveedorCif, @RequestBody ProductosProveedore productosProveedore) {
        ProductosProveedoreId id = new ProductosProveedoreId();
        id.setProductoSku(productoSku);
        id.setProveedorCif(proveedorCif);
        return ResponseEntity.ok(productosProveedoreServices.actualizarProductosProveedore(id, productosProveedore));
    }

    @DeleteMapping("/{productoSku}/{proveedorCif}")
    public ResponseEntity<Void> eliminarProductosProveedore(@PathVariable String productoSku, @PathVariable String proveedorCif) {
        ProductosProveedoreId id = new ProductosProveedoreId();
        id.setProductoSku(productoSku);
        id.setProveedorCif(proveedorCif);
        productosProveedoreServices.eliminarProductosProveedore(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producto/{sku}")
    public ResponseEntity<List<ProductosProveedore>> obtenerPorProductoSku(@PathVariable String sku) {
        return ResponseEntity.ok(productosProveedoreServices.obtenerPorProductoSku(sku));
    }

    @GetMapping("/proveedor/{cif}")
    public ResponseEntity<List<ProductosProveedore>> obtenerPorProveedorCif(@PathVariable String cif) {
        return ResponseEntity.ok(productosProveedoreServices.obtenerPorProveedorCif(cif));
    }
}