package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class ProductoServices{
    private final JpaRepository<Producto, Integer> productoRepository;

    public ProductoServices(JpaRepository<Producto, Integer> productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }
}
