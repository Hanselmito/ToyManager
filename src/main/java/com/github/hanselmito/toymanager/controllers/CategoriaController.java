package com.github.hanselmito.toymanager.controllers;

import com.github.hanselmito.toymanager.model.Categoria;
import com.github.hanselmito.toymanager.services.CategoriaServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaServices categoriaServices;

    public CategoriaController(CategoriaServices categoriaServices) {
        this.categoriaServices = categoriaServices;
    }

    @GetMapping
    public List<Categoria> obtenerCategorias() {
        return categoriaServices.findAll();
    }

    @GetMapping("/{id}")
    public Categoria obtenerCategoriaPorId(@PathVariable Integer id) {
        return categoriaServices.findById(id);
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaServices.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        return categoriaServices.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Integer id) {
        categoriaServices.deleteById(id);
    }
}