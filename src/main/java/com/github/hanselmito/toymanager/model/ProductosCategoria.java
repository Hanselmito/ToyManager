package com.github.hanselmito.toymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos_categoria")
public class ProductosCategoria {
    @EmbeddedId
    private ProductosCategoriaId id;

    @MapsId("productosSku")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productos_sku", nullable = false)
    private Producto productosSku;

    @MapsId("categoriasId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categorias_id", nullable = false)
    @JsonIgnore
    private Categoria categorias;

    public ProductosCategoria() {
    }
}