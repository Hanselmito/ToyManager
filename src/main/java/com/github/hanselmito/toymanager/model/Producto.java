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
    @Column(name = "id", nullable = false)
    private Integer id;

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
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull
    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

    @ManyToMany(mappedBy = "productos")
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "productos")
    private Set<com.github.hanselmito.toymanager.model.Proveedore> proveedores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productos")
    private Set<com.github.hanselmito.toymanager.model.ProductosUsuario> productosUsuarios = new LinkedHashSet<>();

}