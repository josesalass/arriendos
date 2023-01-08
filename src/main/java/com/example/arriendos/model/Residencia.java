package com.example.arriendos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "residencia")
public class Residencia implements Comparable<Residencia>{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    @Lob
    @Column(name = "direccion", nullable = false)
    private String direccion;


    @Lob
    @Column(name = "reestriccion1")
    private Boolean reestriccion1;


    @Lob
    @Column(name = "reestriccion2")
    private Boolean reestriccion2;


    @Lob
    @Column(name = "reestriccion3")
    private Boolean reestriccion3;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPub;

	@Column(name = "imagenresidencia")
	private String imagenResidencia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
	// private String id_usuario;
    private Usuario usuario;

    @OneToMany(mappedBy="residencia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pieza> piezas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getReestriccion1() {
		return reestriccion1;
	}

	public void setReestriccion1(Boolean reestriccion1) {
		this.reestriccion1 = reestriccion1;
	}

	public Boolean getReestriccion2() {
		return reestriccion2;
	}

	public void setReestriccion2(Boolean reestriccion2) {
		this.reestriccion2 = reestriccion2;
	}

	public Boolean getReestriccion3() {
		return reestriccion3;
	}

	public void setReestriccion3(Boolean reestriccion3) {
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

	public Date getFechaPub() {
		return fechaPub;
	}

	public void setFechaPub(Date fechaPub) {
		this.fechaPub = fechaPub;
	}

	public String getImagenResidencia() {
		return imagenResidencia;
	}

	public void setImagenResidencia(String imagenResidencia) {
		this.imagenResidencia = imagenResidencia;
	}

	public String getUsuario() {
		return this.usuario.getId();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Pieza> getPiezas() {
		return piezas;
	}

	public void setPiezas(List<Pieza> piezas) {
		this.piezas = piezas;
	}

	@Override
    public boolean equals(Object obj) {
        return ((Residencia) obj).getFechaPub().equals(getFechaPub());
    }

    @Override
    public int compareTo(Residencia residencia) {
        return getFechaPub().compareTo(residencia.getFechaPub());
    }





}