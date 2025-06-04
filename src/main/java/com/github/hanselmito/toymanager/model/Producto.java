package com.github.hanselmito.toymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;
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
    @JsonProperty("descripcion_corta")
    private String descripcionCorta;

    @NotNull
    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    @JsonProperty("precio_venta")
    private BigDecimal precioVenta;

    @NotNull
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @NotNull
    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

    @ManyToMany(mappedBy = "productos")
    @JsonIgnore
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productoSku")
    @JsonIgnore
    private Set<ProductosProveedore> productosProveedores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productoSku")
    @JsonIgnore
    private Set<ProductosUsuario> productosUsuarios = new LinkedHashSet<>();

    public Producto() {
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public Set<ProductosProveedore> getProductosProveedores() {
        return productosProveedores;
    }

    public void setProductosProveedores(Set<ProductosProveedore> productosProveedores) {
        this.productosProveedores = productosProveedores;
    }

    public Set<ProductosUsuario> getProductosUsuarios() {
        return productosUsuarios;
    }

    public void setProductosUsuarios(Set<ProductosUsuario> productosUsuarios) {
        this.productosUsuarios = productosUsuarios;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "sku='" + sku + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", descripcionCorta='" + descripcionCorta + '\'' +
                ", precioVenta=" + precioVenta +
                ", stock=" + stock +
                ", imagen=" + "Arrays.toString(imagen)" +
                ", categorias=" + categorias +
                '}';
    }
}