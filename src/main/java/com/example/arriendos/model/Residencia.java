package com.example.arriendos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "residencia")
public class Residencia {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    @NotNull
    @Lob
    @Column(name = "fecha_pub")
    private String fechaPub;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy="pieza", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pieza> piezas;







}