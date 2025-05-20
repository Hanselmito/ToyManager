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
public class ProductosCategoriaId implements java.io.Serializable {
    private static final long serialVersionUID = -8406826561154184171L;
    @Size(max = 255)
    @NotNull
    @Column(name = "productos_sku", nullable = false)
    private String productosSku;

    @NotNull
    @Column(name = "categorias_id", nullable = false)
    private Integer categoriasId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductosCategoriaId entity = (ProductosCategoriaId) o;
        return Objects.equals(this.productosSku, entity.productosSku) &&
                Objects.equals(this.categoriasId, entity.categoriasId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productosSku, categoriasId);
    }

}