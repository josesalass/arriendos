package com.example.arriendos.services.Impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.arriendos.services.ResidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.arriendos.model.Residencia;
import com.example.arriendos.model.Usuario;
import com.example.arriendos.repositories.ResidenciaRepository;
import com.example.arriendos.repositories.UsuarioRepository;

@Service
public class ResidenciaServiceImpl implements ResidenciaService {

	@Autowired
	ResidenciaRepository residenciaRepository;

	@Autowired
	UsuarioServiceImpl usuarioService;

	@Override
	public List<Residencia> getAll() {
		return residenciaRepository.findAll();
	}

	@Override
	public Residencia guardarResidencia(Residencia residencia) {
		Date date = new Date();
		System.out.println("fecha: "+ date);
		residencia.setFechaPub(date);

		// este codigo es chantaaa!!
		Usuario newUser = usuarioService.getById("20077281-4");
		residencia.setUsuario(newUser);

		System.out.println("residencia en service: "+ residencia);
		return residenciaRepository.save(residencia);
	}
}
