package com.github.hanselmito.toymanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class ProductosUsuarioId implements java.io.Serializable {
    private static final long serialVersionUID = -3448949800295892260L;
    @NotNull
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @NotNull
    @Column(name = "productos_id", nullable = false)
    private Integer productosId;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getProductosId() {
        return productosId;
    }

    public void setProductosId(Integer productosId) {
        this.productosId = productosId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductosUsuarioId entity = (ProductosUsuarioId) o;
        return Objects.equals(this.usuarioId, entity.usuarioId) &&
                Objects.equals(this.productosId, entity.productosId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, productosId);
    }

}