package com.github.hanselmito.toymanager.model;

import com.github.hanselmito.toymanager.model.Enum.RolTrabajador;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "nif", nullable = false)
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
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolTrabajador rol;

    @NotNull
    @Column(name = "imagen", nullable = false)
    private byte[] imagen;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "ultimo_acceso", nullable = false)
    private Instant ultimoAcceso;

    @OneToMany(mappedBy = "usuarioNif")
    private Set<ProductosUsuario> productosUsuarios = new LinkedHashSet<>();

    public Usuario() {
    }

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

    public RolTrabajador getRol() {
        return rol;
    }

    public void setRol(RolTrabajador rol) {
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

    public Set<ProductosUsuario> getProductosUsuarios() {
        return productosUsuarios;
    }

    public void setProductosUsuarios(Set<ProductosUsuario> productosUsuarios) {
        this.productosUsuarios = productosUsuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nombre, usuario.nombre) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email);
    }


}