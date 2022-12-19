package com.example.arriendos.services;

import java.util.List;

import com.example.arriendos.model.Usuario;

public interface UsuarioService {

	Usuario getUserId(String rut);

	List<Usuario> getAll();

	void save(Usuario user);
	public Usuario guardarUsuario(Usuario usuario);
	
	public Usuario actualizarUser(Usuario usuario);
	
	public void eliminarEstudiante(String rut);

}
