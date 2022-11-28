package com.example.arriendos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.arriendos.model.Residencia;
import com.example.arriendos.repositories.ResidenciaRepository;

@Service
public class ResidenciaServiceImpl implements ResidenciaService {

	@Autowired
	ResidenciaRepository repository;
	
	@Override
	public List<Residencia> getAll() {
		return repository.findAll();
	}
}
