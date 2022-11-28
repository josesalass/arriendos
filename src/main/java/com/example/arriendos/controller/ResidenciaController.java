package com.example.arriendos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.arriendos.model.Residencia;
import com.example.arriendos.services.ResidenciaServiceImpl;

@RequestMapping("/residencia")
@Controller
public class ResidenciaController {
	
	@Autowired
	ResidenciaServiceImpl service;

	@GetMapping("/list")
	public String list(Model model) {
		List<Residencia> residencias = service.getAll();
		
		model.addAttribute("residencias",residencias);
		
		return "Residencias";
	}
	
	
}
