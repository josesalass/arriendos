package com.example.arriendos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.arriendos.model.Pieza;
import com.example.arriendos.services.Impl.PiezaServiceImpl;


@RequestMapping("/piezas")
@Controller
public class PiezaController {
	
	@Autowired
	PiezaServiceImpl service;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Pieza> piezas = service.getAll();		
		model.addAttribute("piezas",piezas);
		
		return "Piezas";
	}

}
