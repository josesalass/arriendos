package com.example.arriendos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
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
    
    

    public Pieza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pieza(Integer id, @NotNull Residencia idResidencia, @NotNull String descripcion, @NotNull Integer espacio,
			@NotNull String img) {
		super();
		this.id = id;
		this.idResidencia = idResidencia;
		this.descripcion = descripcion;
		this.espacio = espacio;
		this.img = img;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Residencia getIdResidencia() {
        return idResidencia;
    }

    public void setIdResidencia(Residencia idResidencia) {
        this.idResidencia = idResidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEspacio() {
        return espacio;
    }

    public void setEspacio(Integer espacio) {
        this.espacio = espacio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}