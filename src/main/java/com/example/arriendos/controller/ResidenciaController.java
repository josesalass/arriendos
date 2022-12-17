package com.example.arriendos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.arriendos.model.Residencia;
import com.example.arriendos.repositories.ResidenciaRepository;
import com.example.arriendos.services.ResidenciaService;
import com.example.arriendos.services.Impl.ResidenciaServiceImpl;

@RequestMapping("/residencia")
@Controller
public class ResidenciaController {
	
	@Autowired
	ResidenciaService residenciaService;

	@GetMapping("")
	public String list(Model model) {
		List<Residencia> residencias = residenciaService.getAll();
		model.addAttribute("residencias",residencias);
		return "Residencias";
	}

	@GetMapping("/create")
	public String create(Model model) {
		Residencia residencia = new Residencia();
		model.addAttribute("residencia",residencia);
		return "createResidencia";
	}

	@PostMapping("/create")
	public String create(Residencia residencia, Model model) {
		residenciaService.guardarResidencia(residencia);
		return "Residencias";
	}

	//lleva a la pagina editResidencia
	@GetMapping("/edit/{id}")
	public String mostrarEdit(@PathVariable(name="id")Integer id, Model model){
		System.out.println("id: "+id);
		Residencia residencia2 = residenciaService.findResidenciaById(id);
		model.addAttribute("residencia",residencia2);
		return "editResidencia";
	}

	//edita la residencia
	@PostMapping("/update/{id}")
	public String edit(Residencia residencia2, Model model) {
		residenciaService.editarResidencia(residencia2);
		return "Residencias";
	}

	
	
}
