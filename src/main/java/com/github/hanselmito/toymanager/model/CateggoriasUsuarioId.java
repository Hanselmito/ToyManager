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
public class CateggoriasUsuarioId implements java.io.Serializable {
    private static final long serialVersionUID = -9074992120764014320L;
    @NotNull
    @Column(name = "categoria_id", nullable = false)
    private Integer categoriaId;

    @NotNull
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CateggoriasUsuarioId entity = (CateggoriasUsuarioId) o;
        return Objects.equals(this.usuarioId, entity.usuarioId) &&
                Objects.equals(this.categoriaId, entity.categoriaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, categoriaId);
    }

}