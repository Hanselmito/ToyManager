package com.github.hanselmito.toymanager.controllers;

import com.github.hanselmito.toymanager.model.Categoria;
import com.github.hanselmito.toymanager.services.CategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaServices categoriaServices;

    /**
     * Obtiene todas las categorías.
     */
    @CrossOrigin
    @GetMapping("/todas")
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaServices.obtenerTodasLasCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    /**
     * Busca categorías por nombre.
     */
    @CrossOrigin
    @GetMapping("/buscar")
    public ResponseEntity<List<Categoria>> buscarCategoriasPorNombre(@RequestParam String nombre) {
        List<Categoria> categorias = categoriaServices.buscarCategoriasPorNombre(nombre);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    /**
     * Busca categorías por categoría padre.
     */
    @CrossOrigin
    @GetMapping("/categoriaPadre/{id}")
    public ResponseEntity<List<Categoria>> buscarCategoriasPorCategoriaPadre(@PathVariable Integer id) {
        List<Categoria> categorias = categoriaServices.buscarCategoriasPorCategoriaPadre(id);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    /**
     * Crea una nueva categoría.
     */
    @CrossOrigin
    @PostMapping("/crear")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaServices.guardarCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    /**
     * Agrega una categoría hija a una categoría padre.
     */
    @CrossOrigin
    @PostMapping("/agregarHija/{idCategoriaPadre}")
    public ResponseEntity<Categoria> agregarCategoriaHija(@PathVariable Integer idCategoriaPadre, @RequestBody Categoria categoriaHija) {
        Categoria categoriaActualizada = categoriaServices.agregarCategoriaHija(idCategoriaPadre, categoriaHija);
        return new ResponseEntity<>(categoriaActualizada, HttpStatus.CREATED);
    }

    /**
     * Elimina una categoría por su ID.
     */
    @CrossOrigin
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        categoriaServices.eliminarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }
}