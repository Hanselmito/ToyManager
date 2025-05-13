package com.github.hanselmito.toymanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
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

}