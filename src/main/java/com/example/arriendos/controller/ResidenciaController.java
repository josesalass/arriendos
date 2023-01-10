package com.example.arriendos.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.arriendos.ResidenciaExcelExporter;
import com.example.arriendos.model.Residencia;
import com.example.arriendos.services.ResidenciaService;

@RequestMapping("/residencia")
@Controller
public class ResidenciaController {
	
	@Autowired
	ResidenciaService residenciaService;

	@Secured("ROLE_USER")
	//PARA LOS OWNERSD
	@GetMapping("")
	public String list(Model model) {
		List<Residencia> residencias = residenciaService.getAllByOwner();

		for (Residencia residencia : residencias) {
			System.out.println("Residencia: "+ residencia.getId() + " - "+ residencia.getReestriccion1());
			System.out.println("Residencia: "+ residencia.getId() + " - "+ residencia.getReestriccion2());
			System.out.println("Residencia: "+ residencia.getId() + " - "+ residencia.getReestriccion3());


		}

		String order = "";
		model.addAttribute("residencias",residencias);
		model.addAttribute("order",order);
		
		return "Residencias";
	}

	//PARA LOS OWNERSD
	@GetMapping("/list")
	public String listU(Model model) {
		List<Residencia> residencias = residenciaService.getAll();

		for (Residencia residencia : residencias) {
			System.out.println("Residencia: /assets/img/imagenesResidencias/"+ residencia.getId() + "-"+ residencia.getImagenResidencia());

		}

		String order = "";
		model.addAttribute("residencias",residencias);
		model.addAttribute("order",order);
		
		return "visuals/Residencias";
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/sort")
	public String listDesc(String order, Model model) {
		System.out.println("order: "+order);
		List<Residencia> residencias = residenciaService.getAllByOwner();
		
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

	@PostMapping("/sortU")
	public String listDescU(String order, Model model) {
		System.out.println("order: "+order);
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
		return "visuals/Residencias";
	}


	@Secured("ROLE_USER")
	@GetMapping("/create")
	public String create(Model model) {
		Residencia residencia = new Residencia();
		model.addAttribute("residencia",residencia);
		return "createResidencia";
	}

	@Secured("ROLE_USER")
	@PostMapping("/create")
	public RedirectView create(Residencia res, @RequestParam("file") MultipartFile imagen, Model model, RedirectAttributes attributes) {

		System.out.println("imagen: "+imagen);
		System.out.println("res: "+res);
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static///assets//img//imagenesResidencias");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {

				attributes.addFlashAttribute("residencia", res);

				res.setImagenResidencia(imagen.getOriginalFilename());
				Residencia resCreada = residenciaService.guardarResidencia(res);
				System.out.println(resCreada.getId());


				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + resCreada.getId() + "-" +imagen.getOriginalFilename());
				java.nio.file.Files.write(rutaCompleta, bytesImg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return new RedirectView("/residencia", true);
	}

	@Secured("ROLE_USER")
	//lleva a la pagina editResidencia
	@GetMapping("/edit/{id}")
	public String mostrarEdit(@PathVariable(name="id")Integer id, Model model){
		System.out.println("id: "+id);
		Residencia residencia = residenciaService.findResidenciaById(id);
		model.addAttribute("residencia",residencia);
		return "editResidencia";
	}

	@Secured("ROLE_USER")
	//edita la residencia
	@PostMapping("/update/{id}")
	public String edit(@Valid Residencia residencia2, Model model) {
		residenciaService.editarResidencia(residencia2);
		return "redirect:/residencia";
	}

	@Secured("ROLE_USER")
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

	@Secured("ROLE_USER")
	@GetMapping("/redirection/{resi}")
	public RedirectView redirect(@PathVariable(name="resi")int id, RedirectAttributes attributes) {
		Residencia res = residenciaService.findResidenciaById(id);
		
		attributes.addFlashAttribute("residencia", res);
		System.out.println("entramos");
		
		return new RedirectView("../../piezas/listRes", true);
	}

	@GetMapping("/redirectionU/{resi}")
	public RedirectView redirectU(@PathVariable(name="resi")int id, RedirectAttributes attributes) {
		Residencia res = residenciaService.findResidenciaById(id);
		
		attributes.addFlashAttribute("residencia", res);
		System.out.println("entramos");
		
		System.out.println("Redirigiendo a listResU");
		return new RedirectView("../../piezas/listResU", true);
	}

	// @GetMapping("/sort")
	// public String sort(Model model) {
	// 	List<Residencia> residencias = residenciaService.getAll();

	// 	Collections.sort(residencias);

	// 	model.addAttribute("residencias",residencias);
	// 	return "Residencias";
	// }

	
	@Secured("ROLE_USER")
	@GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Residencia> residencias = residenciaService.getAllByOwner();
         
        ResidenciaExcelExporter excelExporter = new ResidenciaExcelExporter(residencias);
         
        excelExporter.export(response);
    }

	
	
}
