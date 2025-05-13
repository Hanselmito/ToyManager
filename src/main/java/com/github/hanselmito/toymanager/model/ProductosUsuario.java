package com.github.hanselmito.toymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "productos_usuarios")
public class ProductosUsuario {
    @EmbeddedId
    private ProductosUsuarioId id;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private com.github.hanselmito.toymanager.model.Usuario usuario;

    @MapsId("productosId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productos_id", nullable = false)
    private Producto productos;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombreProducto", nullable = false)
    private String nombreProducto;

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Lob
    @Column(name = "descripcion_Corta", nullable = false)
    private String descripcionCorta;

    @NotNull
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull
    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

}