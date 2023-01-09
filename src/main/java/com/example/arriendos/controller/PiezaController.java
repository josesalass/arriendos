package com.example.arriendos.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
				
		return "visuals/Piezas";
	}

	@Secured("ROLE_USER")
	@GetMapping("listRes/create/{res}")
	public String create(@PathVariable(name="res")int id,Model model) {


		Pieza pieza = new Pieza();

		model.addAttribute("residencia", id);
		model.addAttribute("pieza",pieza);
		return "createPieza";
	}
	

	@Secured("ROLE_USER")
	@PostMapping("listRes/create/{res}")
	public RedirectView create(@PathVariable(name="res")int id, Pieza pieza, @RequestParam("file") MultipartFile imagen, Model model, RedirectAttributes attributes) {

		System.out.println("imagen: "+imagen);

		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static//imagenesPiezas");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {

				Residencia res = residenciaService.findResidenciaById(id);
				attributes.addFlashAttribute("residencia", res);
				pieza.setImg(imagen.getOriginalFilename());
				Pieza pie = service.guardarPieza(pieza, res.getId());
				System.out.println(pie.getDescripcion());


				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + pie.getId() + "-" +imagen.getOriginalFilename());
				java.nio.file.Files.write(rutaCompleta, bytesImg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return new RedirectView("../../listRes", true);
	}	


	@Secured("ROLE_USER")
	//lleva a la pagina editPieza
	@GetMapping("listRes/edit/{res}/{id}")
	public String mostrarEdit(@PathVariable(name="res")int idRes,@PathVariable(name="id")int id, Model model){
		
		Pieza pieza = service.findPiezaById(id);
		
		model.addAttribute("residencia", idRes);
		model.addAttribute("pieza",pieza);
		return "editPieza";
	}

	@Secured("ROLE_USER")
	//edita la residencia
	@PostMapping("listRes/update/{res}/{id}")
	public RedirectView edit(@PathVariable(name="res")int idRes, @Valid Pieza pieza2, Model model, RedirectAttributes attributes) {
		Residencia residencia = residenciaService.findResidenciaById(idRes);
		pieza2.setResidencia(residencia);
		pieza2.setImg("");
		service.editarPieza(pieza2);

		

		attributes.addFlashAttribute("residencia", residencia);

		return new RedirectView("../../../listRes", true);
	}

	@Secured("ROLE_USER")
	//elimina la residencia
	@GetMapping("listRes/delete/{res}/{id}")
	public RedirectView delete(@PathVariable(name="res")int res,@PathVariable(name="id")Integer id, Model model,RedirectAttributes attributes) {

		Pieza pieza = service.findPiezaById(id);
		service.eliminarPieza(pieza);

		File imagen = new File("src//main//resources//static//imagenesPiezas//"+id+"-"+pieza.getImg());
		imagen.delete();

		Residencia residencia = residenciaService.findResidenciaById(res);
		
		attributes.addFlashAttribute("residencia", residencia);

		return new RedirectView("../../../listRes", true);
	};

	@Secured("ROLE_USER")
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

	@GetMapping(value="/listResU")
	public String listResU(Model model,  HttpServletRequest request) {
		System.out.println("Redirigiendo a piezasU");
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		
		if (inputFlashMap != null) {
			Residencia residencia = (Residencia) inputFlashMap.get("residencia");

			model.addAttribute("residencia", residencia);

			model.addAttribute("piezas", residencia.getPiezas());

			return "Piezas";
			
		} else {
			return "redirect:../../residencia/list";
		}
		// Residencia res = residenciaService.findResidenciaById((int)model.getAttribute("residencia"));
		
		// model.addAttribute("piezas", res.getPiezas());
		
	}

	
	

}
