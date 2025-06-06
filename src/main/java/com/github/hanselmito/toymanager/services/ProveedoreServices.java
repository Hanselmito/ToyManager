package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.exception.ResourceNotFoundException;
import com.github.hanselmito.toymanager.model.Proveedore;
import com.github.hanselmito.toymanager.repositories.ProveedoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoreServices {

    @Autowired
    private ProveedoreRepository proveedoreRepository;

    /**
     * Crea un nuevo proveedor.
     *
     * @param proveedor Objeto Proveedore a crear.
     * @return El proveedor creado.
     */
    public Proveedore crearProveedor(Proveedore proveedor) {
        return proveedoreRepository.save(proveedor);
    }

    /**
     * Obtiene un proveedor por su CIF.
     *
     * @param cif El CIF del proveedor a buscar.
     * @return El proveedor encontrado.
     * @throws ResourceNotFoundException Si no se encuentra el proveedor.
     */
    public Proveedore obtenerProveedorPorCif(String cif) {
        return proveedoreRepository.findById(cif)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", "CIF: " + cif));
    }

    /**
     * Actualiza un proveedor existente.
     *
     * @param cif El CIF del proveedor a actualizar.
     * @param proveedorActualizado Objeto Proveedore con los datos actualizados.
     * @return El proveedor actualizado.
     */
    public Proveedore actualizarProveedor(String cif, Proveedore proveedorActualizado) {
        Proveedore proveedor = obtenerProveedorPorCif(cif);
        proveedor.setNombre(proveedorActualizado.getNombre());
        return proveedoreRepository.save(proveedor);
    }

    /**
     * Elimina un proveedor por su CIF.
     *
     * @param cif El CIF del proveedor a eliminar.
     */
    public void eliminarProveedor(String cif) {
        proveedoreRepository.deleteById(cif);
    }

    /**
     * Obtiene todos los proveedores.
     *
     * @return Lista de todos los proveedores.
     */
    public List<Proveedore> obtenerTodosLosProveedores() {
        return proveedoreRepository.findAll();
    }

    /**
     * Busca proveedores por nombre.
     *
     * @param nombre El nombre del proveedor a buscar.
     * @return Lista de proveedores que coinciden con el nombre.
     */
    public List<Proveedore> buscarProveedoresPorNombre(String nombre) {
        return proveedoreRepository.findProveedoresPorNombre(nombre);
    }
}