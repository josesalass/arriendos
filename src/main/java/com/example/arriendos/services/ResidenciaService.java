package com.example.arriendos.services;

import java.util.List;

import com.example.arriendos.model.Residencia;

public interface ResidenciaService {

	List<Residencia> getAll();
	Residencia guardarResidencia(Residencia residencia);
    Residencia findResidenciaById(Integer id);
    void editarResidencia(Residencia residencia);
}
