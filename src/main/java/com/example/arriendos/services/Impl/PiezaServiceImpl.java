package com.example.arriendos.services.Impl;

import java.util.List;

import com.example.arriendos.services.PiezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.arriendos.model.Pieza;
import com.example.arriendos.repositories.PiezaRepository;

@Service
public class PiezaServiceImpl implements PiezaService {
	
	@Autowired
	PiezaRepository repository;
	
	@Override
	public List<Pieza> getAll(){
		return repository.findAll();
	}

}
