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
public class ProductosProveedoreId implements java.io.Serializable {
    private static final long serialVersionUID = 6403375885526787150L;
    @Size(max = 255)
    @NotNull
    @Column(name = "producto_sku", nullable = false)
    private String productoSku;

    @Size(max = 255)
    @NotNull
    @Column(name = "proveedor_cif", nullable = false)
    private String proveedorCif;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductosProveedoreId entity = (ProductosProveedoreId) o;
        return Objects.equals(this.proveedorCif, entity.proveedorCif) &&
                Objects.equals(this.productoSku, entity.productoSku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proveedorCif, productoSku);
    }

}