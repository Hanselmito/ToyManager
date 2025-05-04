package com.github.hanselmito.toymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @NotNull
    @Lob
    @Column(name = "rol", nullable = false)
    private String rol;

    @NotNull
    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "ultimo_acceso", nullable = false)
    private Instant ultimoAcceso;

    @OneToMany(mappedBy = "usuario")
    private Set<CateggoriasUsuario> categgoriasUsuarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<ProductosUsuario> productosUsuarios = new LinkedHashSet<>();

    @ManyToMany
    private Set<Proveedore> proveedores = new LinkedHashSet<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Instant getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Instant ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public Set<CateggoriasUsuario> getCateggoriasUsuarios() {
        return categgoriasUsuarios;
    }

    public void setCateggoriasUsuarios(Set<CateggoriasUsuario> categgoriasUsuarios) {
        this.categgoriasUsuarios = categgoriasUsuarios;
    }

    public Set<ProductosUsuario> getProductosUsuarios() {
        return productosUsuarios;
    }

    public void setProductosUsuarios(Set<ProductosUsuario> productosUsuarios) {
        this.productosUsuarios = productosUsuarios;
    }

    public Set<Proveedore> getProveedores() {
        return proveedores;
    }

    public void setProveedores(Set<Proveedore> proveedores) {
        this.proveedores = proveedores;
    }
}