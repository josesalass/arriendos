package com.example.arriendos.services.Impl;

import java.util.List;

import com.example.arriendos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.arriendos.model.Usuario;
import com.example.arriendos.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository repository;
	
	@Override
	public List<Usuario> getAll(){
		return repository.findAll();
	}
	
	@Override
	public Usuario getById(String rut){
		return repository.getReferenceById(rut);
	}

}
