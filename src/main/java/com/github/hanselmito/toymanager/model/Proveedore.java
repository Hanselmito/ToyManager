package com.github.hanselmito.toymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "proveedores")
public class Proveedore {
    @Id
    @Size(max = 255)
    @Column(name = "cif", nullable = false)
    private String cif;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "proveedorCif")
    @JsonIgnore
    private Set<ProductosProveedore> productosProveedores = new LinkedHashSet<>();

    public Proveedore() {
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<ProductosProveedore> getProductosProveedores() {
        return productosProveedores;
    }

    public void setProductosProveedores(Set<ProductosProveedore> productosProveedores) {
        this.productosProveedores = productosProveedores;
    }

    @Override
    public String toString() {
        return "Proveedore{" +
                "cif='" + cif + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}