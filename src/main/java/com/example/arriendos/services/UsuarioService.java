package com.example.arriendos.services;

import java.util.List;

import com.example.arriendos.model.Usuario;

public interface UsuarioService {

	Usuario getById(String rut);

	List<Usuario> getAll();

}
