package com.example.arriendos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Size(max = 10)
    @Column(name = "rut", nullable = false, length = 10)
    private String id;

    @NotNull
    @Lob
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Lob
    @Column(name = "apellido1", nullable = false)
    private String apellido1;

    @NotNull
    @Lob
    @Column(name = "apellido2", nullable = false)
    private String apellido2;

    @Size(max = 10)
    @NotNull
    @Column(name = "nacimiento", nullable = false, length = 10)
    private String nacimiento;

    @NotNull
    @Lob
    @Column(name = "institucion", nullable = false)
    private String institucion;

    @NotNull
    @Lob
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotNull
    @Lob
    @Column(name = "comuna", nullable = false)
    private String comuna;

    @NotNull
    @Lob
    @Column(name = "img", nullable = false)
    private String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}