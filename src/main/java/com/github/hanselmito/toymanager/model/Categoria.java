package com.github.hanselmito.toymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "categoria_padre_id")
    @JsonIgnore
    private Categoria categoriaPadre;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "categoriaPadre")
    @JsonIgnore
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "productos_categoria",
            joinColumns = @JoinColumn(name = "categorias_id"),
            inverseJoinColumns = @JoinColumn(name = "productos_sku"))
    @JsonIgnore
    private Set<com.github.hanselmito.toymanager.model.Producto> productos = new LinkedHashSet<>();

    @PrePersist
    public void prePersist() {
        if (fechaCreacion == null) {
            fechaCreacion = Instant.now();
        }
    }

    public Categoria() {
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoriaPadre=" + categoriaPadre +
                ", fechaCreacion=" + fechaCreacion +
                ", categorias=" + categorias +
                '}';
    }
}