package com.github.hanselmito.toymanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProductosUsuarioId implements java.io.Serializable {
    private static final long serialVersionUID = -4538802414095224888L;
    @NotNull
    @Column(name = "usuario_nif", nullable = false)
    private Integer usuarioNif;

    @Size(max = 255)
    @NotNull
    @Column(name = "producto_sku", nullable = false)
    private String productoSku;

    public ProductosUsuarioId() {
    }

    public ProductosUsuarioId(Integer usuarioNif, String productoSku) {
        this.usuarioNif = usuarioNif;
        this.productoSku = productoSku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductosUsuarioId entity = (ProductosUsuarioId) o;
        return Objects.equals(this.usuarioNif, entity.usuarioNif) &&
                Objects.equals(this.productoSku, entity.productoSku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioNif, productoSku);
    }

}