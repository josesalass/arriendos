package com.example.arriendos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pieza")
public class Pieza {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_residencia", nullable = false)
    private Residencia idResidencia;

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "espacio", nullable = false)
    private Integer espacio;

    @NotNull
    @Lob
    @Column(name = "img", nullable = false)
    private String img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_residencia", nullable = false)
    private Pieza pieza;

    @ManyToMany
    @JoinTable(
            name = "piezacat",
            joinColumns = @JoinColumn(name = "id_pieza"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    List<Categoria> categoriasPiezas = new ArrayList<>();

    public void addCategoria(Categoria categoria) {
        categoriasPiezas.add(categoria);
        categoria.getPiezas().add(this);
    }

    public void removeCategoria(Categoria categoria) {
        categoriasPiezas.remove(categoria);
        categoria.getPiezas().remove(this);
    }




}