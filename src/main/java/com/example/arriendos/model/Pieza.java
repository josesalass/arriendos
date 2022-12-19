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

    /*@NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_residencia", nullable = false)
    private Residencia idResidencia;*/

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    
    @NotNull
    @Column(name = "espacio", nullable = false)
    private Integer espacio;
    
    @NotNull
    @Column(name = "precio")
    private Integer precio;

    @NotNull
    @Lob
    @Column(name = "img", nullable = false)
    private String img;
    

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_residencia", nullable = false)
    private Residencia residencia;

    @ManyToMany(fetch = FetchType.EAGER)
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}

	public List<Categoria> getCategoriasPiezas() {
		return categoriasPiezas;
	}

	public void setCategoriasPiezas(List<Categoria> categoriasPiezas) {
		this.categoriasPiezas = categoriasPiezas;
	}
    
    




}