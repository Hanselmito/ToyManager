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

    /**
     * Crea un nuevo proveedor.
     *
     * @param proveedor Objeto Proveedore a crear.
     * @return El proveedor creado.
     */
    @PostMapping("/crear")
    public ResponseEntity<Proveedore> crearProveedor(@RequestBody Proveedore proveedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedoreServices.crearProveedor(proveedor));
    }

    /**
     * Obtiene un proveedor por su CIF.
     *
     * @param cif El CIF del proveedor a buscar.
     * @return El proveedor encontrado.
     */
    @GetMapping("/{cif}")
    public ResponseEntity<Proveedore> obtenerProveedor(@PathVariable String cif) {
        return ResponseEntity.ok(proveedoreServices.obtenerProveedorPorCif(cif));
    }

    /**
     * Actualiza un proveedor existente.
     *
     * @param cif El CIF del proveedor a actualizar.
     * @param proveedor Objeto Proveedore con los datos actualizados.
     * @return El proveedor actualizado.
     */
    @PutMapping("/actualizar/{cif}")
    public ResponseEntity<Proveedore> actualizarProveedor(@PathVariable String cif, @RequestBody Proveedore proveedor) {
        return ResponseEntity.ok(proveedoreServices.actualizarProveedor(cif, proveedor));
    }

    /**
     * Elimina un proveedor por su CIF.
     *
     * @param cif El CIF del proveedor a eliminar.
     * @return Respuesta vacía con estado 204 No Content.
     */
    @DeleteMapping("/eliminar/{cif}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable String cif) {
        proveedoreServices.eliminarProveedor(cif);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene todos los proveedores.
     *
     * @return Lista de todos los proveedores.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<Proveedore>> obtenerTodosLosProveedores() {
        return ResponseEntity.ok(proveedoreServices.obtenerTodosLosProveedores());
    }

    /**
     * Busca proveedores por nombre.
     *
     * @param nombre Nombre del proveedor a buscar.
     * @return Lista de proveedores que coinciden con el nombre.
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Proveedore>> buscarProveedoresPorNombre(@RequestParam String nombre) {
        List<Proveedore> proveedores = proveedoreServices.buscarProveedoresPorNombre(nombre);
        return ResponseEntity.ok(proveedores);
    }
}