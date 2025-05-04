package com.github.hanselmito.toymanager.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "usuario_proveedor")
public class UsuarioProveedor {
    @EmbeddedId
    private UsuarioProveedorId id;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private com.github.hanselmito.toymanager.model.Usuario usuario;

    @MapsId("proveedorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedore proveedor;

    public UsuarioProveedorId getId() {
        return id;
    }

    public void setId(UsuarioProveedorId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Proveedore getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedore proveedor) {
        this.proveedor = proveedor;
    }
}