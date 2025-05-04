package com.github.hanselmito.toymanager.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "productos_categorias")
public class ProductosCategoria {
    @EmbeddedId
    private ProductosCategoriaId id;

    @MapsId("productosId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productos_id", nullable = false)
    private Producto productos;

    @MapsId("categoriasId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "categorias_id", nullable = false)
    private Categoria categorias;

    public ProductosCategoriaId getId() {
        return id;
    }

    public void setId(ProductosCategoriaId id) {
        this.id = id;
    }

    public Producto getProductos() {
        return productos;
    }

    public void setProductos(Producto productos) {
        this.productos = productos;
    }

    public Categoria getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria categorias) {
        this.categorias = categorias;
    }
}