package com.github.hanselmito.toymanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.util.Objects;


@Embeddable
public class ProductosCategoriaId implements java.io.Serializable {
    private static final long serialVersionUID = -2025872717238898366L;
    @NotNull
    @Column(name = "productos_id", nullable = false)
    private Integer productosId;

    @NotNull
    @Column(name = "categorias_id", nullable = false)
    private Integer categoriasId;

    public Integer getProductosId() {
        return productosId;
    }

    public void setProductosId(Integer productosId) {
        this.productosId = productosId;
    }

    public Integer getCategoriasId() {
        return categoriasId;
    }

    public void setCategoriasId(Integer categoriasId) {
        this.categoriasId = categoriasId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductosCategoriaId entity = (ProductosCategoriaId) o;
        return Objects.equals(this.categoriasId, entity.categoriasId) &&
                Objects.equals(this.productosId, entity.productosId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoriasId, productosId);
    }

}