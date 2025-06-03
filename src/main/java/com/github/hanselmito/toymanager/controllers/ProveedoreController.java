package com.github.hanselmito.toymanager.controllers;

import com.github.hanselmito.toymanager.model.Proveedore;
import com.github.hanselmito.toymanager.services.ProveedoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedoreController {

    @Autowired
    private ProveedoreServices proveedoreServices;

    @PostMapping
    public ResponseEntity<Proveedore> crearProveedor(@RequestBody Proveedore proveedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedoreServices.crearProveedor(proveedor));
    }

    @GetMapping("/{cif}")
    public ResponseEntity<Proveedore> obtenerProveedor(@PathVariable String cif) {
        return ResponseEntity.ok(proveedoreServices.obtenerProveedorPorCif(cif));
    }

    @PutMapping("/{cif}")
    public ResponseEntity<Proveedore> actualizarProveedor(@PathVariable String cif, @RequestBody Proveedore proveedor) {
        return ResponseEntity.ok(proveedoreServices.actualizarProveedor(cif, proveedor));
    }

    @DeleteMapping("/{cif}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable String cif) {
        proveedoreServices.eliminarProveedor(cif);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Proveedore>> obtenerTodosLosProveedores() {
        return ResponseEntity.ok(proveedoreServices.obtenerTodosLosProveedores());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Proveedore>> buscarProveedoresPorNombre(@RequestParam String nombre) {
        List<Proveedore> proveedores = proveedoreServices.buscarProveedoresPorNombre(nombre);
        return ResponseEntity.ok(proveedores);
    }
}