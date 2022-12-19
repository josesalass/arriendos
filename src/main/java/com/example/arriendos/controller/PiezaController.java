package com.example.arriendos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.arriendos.model.Pieza;
import com.example.arriendos.model.Residencia;
import com.example.arriendos.services.ResidenciaService;
import com.example.arriendos.services.Impl.PiezaServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;



@RequestMapping("/piezas")
@Controller
public class PiezaController {
	
	@Autowired
	PiezaServiceImpl service;

	@Autowired
	ResidenciaService residenciaService;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Pieza> piezas = service.getAll();	
		
		model.addAttribute("piezas",piezas);
				
		return "Piezas";
	}

	@GetMapping(value="/listRes")
	public String listRes(Model model,  HttpServletRequest request) {
		
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		
		if (inputFlashMap != null) {
			Residencia residencia = (Residencia) inputFlashMap.get("residencia");
			model.addAttribute("piezas", residencia.getPiezas());

			return "Piezas";
			
		} else {
			return "redirect:../../residencia";
		}
		// Residencia res = residenciaService.findResidenciaById((int)model.getAttribute("residencia"));
		
		// model.addAttribute("piezas", res.getPiezas());
		
	}
	

}
