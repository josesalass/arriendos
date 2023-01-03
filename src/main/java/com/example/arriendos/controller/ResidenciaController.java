package com.example.arriendos.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.arriendos.model.Residencia;
import com.example.arriendos.services.ResidenciaService;

@RequestMapping("/residencia")
@Controller
public class ResidenciaController {
	
	@Autowired
	ResidenciaService residenciaService;

	@GetMapping("")
	public String list(Model model) {
		List<Residencia> residencias = residenciaService.getAll();
		String order = "";
		model.addAttribute("residencias",residencias);
		model.addAttribute("order",order);
		
		return "Residencias";
	}

	@PostMapping("/sort")
	public String listDesc(String order, Model model) {
		List<Residencia> residencias = residenciaService.getAll();
		
		if(order.equals("desc")){
			Collections.sort(residencias, new Comparator<Residencia>() {
				public int compare(Residencia res1, Residencia res2) {
					return res2.getFechaPub().compareTo(res1.getFechaPub());
				}
			});
		}else{
			Collections.sort(residencias);
		}
		model.addAttribute("residencias",residencias);
		return "Residencias";
	}


	@GetMapping("/create")
	public String create(Model model) {
		Residencia residencia = new Residencia();
		System.out.println(residencia.getId());
		model.addAttribute("residencia",residencia);
		return "createResidencia";
	}

	@PostMapping("/create")
	public String create(Residencia residencia, Model model) {
		System.out.println(residencia.getDescripcion());
		residenciaService.guardarResidencia(residencia);
		return "redirect:/residencia";
	}

	//lleva a la pagina editResidencia
	@GetMapping("/edit/{id}")
	public String mostrarEdit(@PathVariable(name="id")Integer id, Model model){
		System.out.println("id: "+id);
		Residencia residencia = residenciaService.findResidenciaById(id);
		model.addAttribute("residencia",residencia);
		return "editResidencia";
	}

	//edita la residencia
	@PostMapping("/update/{id}")
	public String edit(@Valid Residencia residencia2, Model model) {
		residenciaService.editarResidencia(residencia2);
		return "redirect:/residencia";
	}

	//elimina la residencia
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name="id")Integer id, Model model) {
		residenciaService.eliminarResidencia(id);
		return "redirect:/residencia";
	}

	// @GetMapping("/redirect/{resi}")
	// public String redirect(@PathVariable(name="resi")int id, Model model) {
	// 	Residencia res = residenciaService.findResidenciaById(id);
	// 	System.out.println("entramos");
	// 	return "redirect:/residencia";
	// }

	@GetMapping("/redirection/{resi}")
	public RedirectView redirect(@PathVariable(name="resi")int id, RedirectAttributes attributes) {
		Residencia res = residenciaService.findResidenciaById(id);
		
		attributes.addFlashAttribute("residencia", res);
		System.out.println("entramos");
		
		return new RedirectView("../../piezas/listRes", true);
	}

	// @GetMapping("/sort")
	// public String sort(Model model) {
	// 	List<Residencia> residencias = residenciaService.getAll();

	// 	Collections.sort(residencias);

	// 	model.addAttribute("residencias",residencias);
	// 	return "Residencias";
	// }

	


	
	
}
