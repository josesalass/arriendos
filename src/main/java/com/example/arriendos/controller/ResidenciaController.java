package com.example.arriendos.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

	


	
	
}
