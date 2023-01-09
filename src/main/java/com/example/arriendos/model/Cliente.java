package com.example.arriendos.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @Size(max = 10)
    @Column(name = "rut", nullable = false, length = 10)
    private String id;
    
    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "apellido1", nullable = false)
    private String apellido1;

    @NotNull
    @Column(name = "apellido2", nullable = false)
    private String apellido2;

    @Size(max = 10)
    @NotNull
    @Column(name = "nacimiento", nullable = false, length = 10)
    private String nacimiento;

    @NotNull
    @Column(name = "institucion", nullable = false)
    private String institucion;

    @NotNull
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotNull
    @Column(name = "comuna", nullable = false)
    private String comuna;

    @NotNull
    @Column(name = "img", nullable = false)
    private String img;    


}