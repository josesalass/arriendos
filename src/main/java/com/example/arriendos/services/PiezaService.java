package com.example.arriendos.services;

import java.util.List;

import com.example.arriendos.model.Pieza;

public interface PiezaService {

	List<Pieza> getAll();

	Pieza guardarPieza(Pieza pieza, int idResidencia);

	Pieza findPiezaById(Integer id);

	void editarPieza(Pieza pieza) ;

	void eliminarPieza(Pieza pieza);

}
