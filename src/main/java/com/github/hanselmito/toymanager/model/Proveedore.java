package com.github.hanselmito.toymanager.model;

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
    private Set<ProductosProveedore> productosProveedores = new LinkedHashSet<>();

}