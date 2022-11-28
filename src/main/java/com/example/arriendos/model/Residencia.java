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
    @Lob
    @Column(name = "direccion", nullable = false)
    private String direccion;


    @Lob
    @Column(name = "reestriccion1")
    private String reestriccion1;


    @Lob
    @Column(name = "reestriccion2")
    private String reestriccion2;


    @Lob
    @Column(name = "reestriccion3")
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

    @OneToMany(mappedBy="residencia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

	public String getFechaPub() {
		return fechaPub;
	}

	public void setFechaPub(String fechaPub) {
		this.fechaPub = fechaPub;
	}

	public Usuario getUsuario() {
		return usuario;
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





}