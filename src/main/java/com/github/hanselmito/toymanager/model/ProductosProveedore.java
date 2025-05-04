package com.github.hanselmito.toymanager.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "productos_proveedores")
public class ProductosProveedore {
    @EmbeddedId
    private ProductosProveedoreId id;

    @MapsId("productoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @MapsId("proveedorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private com.github.hanselmito.toymanager.model.Proveedore proveedor;

    public ProductosProveedoreId getId() {
        return id;
    }

    public void setId(ProductosProveedoreId id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedore getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedore proveedor) {
        this.proveedor = proveedor;
    }
}