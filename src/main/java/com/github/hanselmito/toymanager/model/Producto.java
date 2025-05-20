package com.github.hanselmito.toymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @Size(max = 255)
    @Column(name = "sku", nullable = false)
    private String sku;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Lob
    @Column(name = "descripcion_corta", nullable = false)
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

    @ManyToMany(mappedBy = "productos")
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productoSku")
    private Set<ProductosProveedore> productosProveedores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productoSku")
    private Set<ProductosUsuario> productosUsuarios = new LinkedHashSet<>();

}