package com.github.hanselmito.toymanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProductosProveedoreId implements java.io.Serializable {
    private static final long serialVersionUID = -6494209449491586510L;
    @NotNull
    @Column(name = "producto_id", nullable = false)
    private Integer productoId;

    @NotNull
    @Column(name = "proveedor_id", nullable = false)
    private Integer proveedorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductosProveedoreId entity = (ProductosProveedoreId) o;
        return Objects.equals(this.proveedorId, entity.proveedorId) &&
                Objects.equals(this.productoId, entity.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proveedorId, productoId);
    }

}