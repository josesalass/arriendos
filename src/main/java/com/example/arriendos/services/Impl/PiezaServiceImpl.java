package com.example.arriendos.services.Impl;

import java.util.Date;
import java.util.List;

import com.example.arriendos.services.PiezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.arriendos.model.Pieza;
import com.example.arriendos.model.Residencia;
import com.example.arriendos.repositories.PiezaRepository;
import com.example.arriendos.repositories.ResidenciaRepository;

@Service
public class PiezaServiceImpl implements PiezaService {
	
	@Autowired
	PiezaRepository repository;

	@Autowired
	ResidenciaServiceImpl residenciaService; 
	
	@Override
	public List<Pieza> getAll(){
		return repository.findAll();
	}

	@Override
	public Pieza findPiezaById(Integer id) {
		Pieza pieza = repository.findById(id).get();
		return pieza;
	}

	@Override
	public void editarPieza(Pieza pieza) {
		repository.save(pieza);
	}

	@Override
	public Pieza guardarPieza(Pieza pieza) {
		
		System.out.println("pieza en service: "+ pieza);
		return repository.save(pieza);
	}

	@Override
	public void eliminarPieza(Pieza pieza) {
		Residencia res = residenciaService.findResidenciaById(pieza.getResidencia().getId());		

		res.getPiezas().remove(pieza);

		residenciaService.editarResidencia(res);

		System.out.println(res.getPiezas());
		repository.deleteById(pieza.getId());
	}

}
