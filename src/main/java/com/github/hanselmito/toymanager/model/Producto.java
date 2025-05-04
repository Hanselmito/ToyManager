package com.github.hanselmito.toymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<Proveedore> getProveedores() {
        return proveedores;
    }

    public void setProveedores(Set<Proveedore> proveedores) {
        this.proveedores = proveedores;
    }

    public Set<ProductosUsuario> getProductosUsuarios() {
        return productosUsuarios;
    }

    public void setProductosUsuarios(Set<ProductosUsuario> productosUsuarios) {
        this.productosUsuarios = productosUsuarios;
    }
}