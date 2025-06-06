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

    /**
     * Crea una nueva relación entre un producto y un proveedor.
     *
     * @param productosProveedore Objeto ProductosProveedore a crear.
     * @return El objeto creado con estado 201 Created.
     */
    @PostMapping("/crear")
    public ResponseEntity<ProductosProveedore> crearProductosProveedore(@RequestBody ProductosProveedore productosProveedore) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productosProveedoreServices.crearProductosProveedore(productosProveedore));
    }

    /**
     * Obtiene un producto proveedor por su ID compuesto (productoSku y proveedorCif).
     *
     * @param productoSku SKU del producto.
     * @param proveedorCif CIF del proveedor.
     * @return El objeto ProductosProveedore encontrado.
     */
    @GetMapping("/{productoSku}/{proveedorCif}")
    public ResponseEntity<ProductosProveedore> obtenerProductosProveedorePorId(@PathVariable String productoSku, @PathVariable String proveedorCif) {
        ProductosProveedoreId id = new ProductosProveedoreId();
        id.setProductoSku(productoSku);
        id.setProveedorCif(proveedorCif);
        return ResponseEntity.ok(productosProveedoreServices.obtenerProductosProveedorePorId(id));
    }

    /**
     * Actualiza una relación entre un producto y un proveedor.
     *
     * @param productoSku SKU del producto.
     * @param proveedorCif CIF del proveedor.
     * @param productosProveedore Objeto ProductosProveedore con los datos actualizados.
     * @return El objeto actualizado.
     */
    @PutMapping("/{productoSku}/{proveedorCif}")
    public ResponseEntity<ProductosProveedore> actualizarProductosProveedore(@PathVariable String productoSku, @PathVariable String proveedorCif, @RequestBody ProductosProveedore productosProveedore) {
        ProductosProveedoreId id = new ProductosProveedoreId();
        id.setProductoSku(productoSku);
        id.setProveedorCif(proveedorCif);
        return ResponseEntity.ok(productosProveedoreServices.actualizarProductosProveedore(id, productosProveedore));
    }

    /**
     * Elimina una relación entre un producto y un proveedor.
     *
     * @param productoSku SKU del producto.
     * @param proveedorCif CIF del proveedor.
     * @return Respuesta vacía con estado 204 No Content.
     */
    @DeleteMapping("/{productoSku}/{proveedorCif}")
    public ResponseEntity<Void> eliminarProductosProveedore(@PathVariable String productoSku, @PathVariable String proveedorCif) {
        ProductosProveedoreId id = new ProductosProveedoreId();
        id.setProductoSku(productoSku);
        id.setProveedorCif(proveedorCif);
        productosProveedoreServices.eliminarProductosProveedore(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene todos los productos proveedores.
     *
     * @return Lista de todos los productos proveedores.
     */
    @GetMapping("/producto/{sku}")
    public ResponseEntity<List<ProductosProveedore>> obtenerPorProductoSku(@PathVariable String sku) {
        return ResponseEntity.ok(productosProveedoreServices.obtenerPorProductoSku(sku));
    }

    /**
     * Obtiene todos los productos proveedores asociados a un proveedor por su CIF.
     *
     * @param cif CIF del proveedor.
     * @return Lista de ProductosProveedore asociados al proveedor.
     */
    @GetMapping("/proveedor/{cif}")
    public ResponseEntity<List<ProductosProveedore>> obtenerPorProveedorCif(@PathVariable String cif) {
        return ResponseEntity.ok(productosProveedoreServices.obtenerPorProveedorCif(cif));
    }

    /**
     * Obtiene todos los productos proveedores.
     *
     * @return Lista de todos los productos proveedores.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<ProductosProveedore>> obtenerTodosLosProductosProveedores() {
        return ResponseEntity.ok(productosProveedoreServices.obtenerTodosLosProductosProveedores());
    }
}