package com.example.arriendos.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Size(max = 10)
    @Column(name = "rut", nullable = false, length = 10)
    private String id;
    
    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Size(max = 30)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Size(max = 30)
    @NotNull
    @Column(name = "apellido1", nullable = false, length = 30)
    private String apellido1;

    @Size(max = 30)
    @NotNull
    @Column(name = "apellido2", nullable = false, length = 30)
    private String apellido2;

    @Size(max = 10)
    @NotNull
    @Column(name = "nacimiento", nullable = false, length = 10)
    private String nacimiento;

    //@Lob
    @Column(name = "img", nullable = false)
    private String img;

    @OneToMany(mappedBy="usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Residencia> residencias;

    
    
    
    public Usuario(@Size(max = 10) String id, String password, @Size(max = 30) @NotNull String nombre,
			@Size(max = 30) @NotNull String apellido1, @Size(max = 30) @NotNull String apellido2,
			@Size(max = 10) @NotNull String nacimiento, @NotNull String img) {
		super();
		this.id = id;
		this.password = password;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nacimiento = nacimiento;
		this.img = img;
	}
    
    

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}



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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public List<Residencia> getResidencias() {
        return residencias;
    }

    public void setResidencias(List<Residencia> residencias) {
        this.residencias = residencias;
    }
    
    

}