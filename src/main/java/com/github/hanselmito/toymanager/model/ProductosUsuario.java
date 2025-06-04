package com.github.hanselmito.toymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @MapsId("usuarioNif")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_nif", nullable = false)
    @JsonIgnore
    private Usuario usuarioNif;

    @MapsId("productoSku")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_sku", nullable = false)
    private Producto productoSku;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre_producto", nullable = false)
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
    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @NotNull
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @NotNull
    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    public ProductosUsuario() {
    }
}