package com.example.arriendos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.arriendos.model.Pieza;
import com.example.arriendos.model.Residencia;
import com.example.arriendos.services.ResidenciaService;
import com.example.arriendos.services.Impl.PiezaServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;



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

	@GetMapping("listRes/create/{res}")
	public String create(@PathVariable(name="res")int id,Model model) {


		Pieza pieza = new Pieza();

		model.addAttribute("residencia", id);
		model.addAttribute("pieza",pieza);
		return "createPieza";
	}

	@PostMapping("listRes/create/{res}")
	public RedirectView create(@PathVariable(name="res")int id,Pieza pieza, Model model, RedirectAttributes attributes) {

		pieza.setImg("");
		Residencia res = residenciaService.findResidenciaById(id);


		attributes.addFlashAttribute("residencia", res);

		Pieza pie = service.guardarPieza(pieza, res.getId());

		System.out.println(pie.getDescripcion());

		return new RedirectView("../../listRes", true);
	}	


	//lleva a la pagina editPieza
	@GetMapping("listRes/edit/{res}/{id}")
	public String mostrarEdit(@PathVariable(name="res")int idRes,@PathVariable(name="id")int id, Model model){
		
		Pieza pieza = service.findPiezaById(id);
		
		model.addAttribute("residencia", idRes);
		model.addAttribute("pieza",pieza);
		return "editPieza";
	}

	//edita la residencia
	@PostMapping("listRes/update/{res}/{id}")
	public RedirectView edit(@PathVariable(name="res")int idRes, @Valid Pieza pieza2, Model model, RedirectAttributes attributes) {
		Residencia residencia = residenciaService.findResidenciaById(idRes);
		pieza2.setResidencia(residencia);
		service.editarPieza(pieza2);

		

		attributes.addFlashAttribute("residencia", residencia);

		return new RedirectView("../../../listRes", true);
	}

	//elimina la residencia
	@GetMapping("listRes/delete/{res}/{id}")
	public RedirectView delete(@PathVariable(name="res")int res,@PathVariable(name="id")Integer id, Model model,RedirectAttributes attributes) {

		Pieza pieza = service.findPiezaById(id);
		service.eliminarPieza(pieza);

		Residencia residencia = residenciaService.findResidenciaById(res);
		
		attributes.addFlashAttribute("residencia", residencia);

		return new RedirectView("../../../listRes", true);
	};

	@GetMapping(value="/listRes")
	public String listRes(Model model,  HttpServletRequest request) {
		
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		
		if (inputFlashMap != null) {
			Residencia residencia = (Residencia) inputFlashMap.get("residencia");

			model.addAttribute("residencia", residencia);

			model.addAttribute("piezas", residencia.getPiezas());

			return "Piezas";
			
		} else {
			return "redirect:../../residencia";
		}
		// Residencia res = residenciaService.findResidenciaById((int)model.getAttribute("residencia"));
		
		// model.addAttribute("piezas", res.getPiezas());
		
	}
	

}
