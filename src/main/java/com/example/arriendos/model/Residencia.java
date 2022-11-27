package com.example.arriendos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "residencia")
public class Residencia {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @NotNull
    @Lob
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotNull
    @Lob
    @Column(name = "reestriccion1", nullable = false)
    private String reestriccion1;

    @NotNull
    @Lob
    @Column(name = "reestriccion2", nullable = false)
    private String reestriccion2;

    @NotNull
    @Lob
    @Column(name = "reestriccion3", nullable = false)
    private String reestriccion3;

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Lob
    @Column(name = "institucion", nullable = false)
    private String institucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReestriccion1() {
        return reestriccion1;
    }

    public void setReestriccion1(String reestriccion1) {
        this.reestriccion1 = reestriccion1;
    }

    public String getReestriccion2() {
        return reestriccion2;
    }

    public void setReestriccion2(String reestriccion2) {
        this.reestriccion2 = reestriccion2;
    }

    public String getReestriccion3() {
        return reestriccion3;
    }

    public void setReestriccion3(String reestriccion3) {
        this.reestriccion3 = reestriccion3;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

}