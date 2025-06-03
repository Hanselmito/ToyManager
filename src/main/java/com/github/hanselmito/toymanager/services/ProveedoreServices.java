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

    public Proveedore crearProveedor(Proveedore proveedor) {
        return proveedoreRepository.save(proveedor);
    }

    public Proveedore obtenerProveedorPorCif(String cif) {
        return proveedoreRepository.findById(cif)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", "CIF: " + cif));
    }

    public Proveedore actualizarProveedor(String cif, Proveedore proveedorActualizado) {
        Proveedore proveedor = obtenerProveedorPorCif(cif);
        proveedor.setNombre(proveedorActualizado.getNombre());
        return proveedoreRepository.save(proveedor);
    }

    public void eliminarProveedor(String cif) {
        proveedoreRepository.deleteById(cif);
    }

    public List<Proveedore> obtenerTodosLosProveedores() {
        return proveedoreRepository.findAll();
    }

    public List<Proveedore> buscarProveedoresPorNombre(String nombre) {
        return proveedoreRepository.findProveedoresPorNombre(nombre);
    }
}