package com.example.arriendos.services.Impl;

import java.util.List;

import com.example.arriendos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.arriendos.model.Residencia;
import com.example.arriendos.model.Usuario;
import com.example.arriendos.repositories.ResidenciaRepository;
import com.example.arriendos.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
		
	@Override
	public List<Usuario> getAll(){
		return repository.findAll();
	}
	
	@Override
	public Usuario getUserId(String rut){
		Usuario user = repository.findById(rut).get();
		return user;
	}
	
	@Override
	public Usuario save(Usuario user) {
		return repository.save(user);
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return repository.save(usuario);
	}

	@Override
	public Usuario actualizarUser(Usuario usuario) {
		// TODO Auto-generated method stub
		return repository.save(usuario);
	}

	@Override
	public void eliminarEstudiante(String rut) {
		// TODO Auto-generated method stub

		Usuario usuario = repository.getById(rut);
		repository.delete(usuario);
		
	}

}
