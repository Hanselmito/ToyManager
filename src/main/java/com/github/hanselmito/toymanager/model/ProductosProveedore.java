package com.github.hanselmito.toymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "productos_proveedores")
public class ProductosProveedore {
    @EmbeddedId
    private ProductosProveedoreId id;

    @MapsId("productoSku")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_sku", nullable = false)
    private Producto productoSku;

    @MapsId("proveedorCif")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "proveedor_cif", nullable = false)
    private Proveedore proveedorCif;

    @NotNull
    @Column(name = "precio_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCompra;

    public ProductosProveedore() {
    }
}