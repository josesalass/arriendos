package com.example.arriendos.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.arriendos.model.Residencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.arriendos.model.Usuario;
import com.example.arriendos.services.UsuarioService;
import com.example.arriendos.services.Impl.UsuarioServiceImpl;

import javax.validation.Valid;

@RequestMapping("/usuario")
@Controller
public class UsuarioController {
	
	@Autowired 
	UsuarioService service;
	
	RedirectAttributes redirectAttrs;
	
	@Secured("ROLE_USER")
	@GetMapping("/user")
	public String getUser(Model model) {
		
		model.addAttribute("usuario", service.getAll());
		
		return "usuarios";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/perfil")
	public String usuariosPerfil(Model model) {
		
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usuario", service.getUserId(user.getUsername()));
		
		return "perfil";
	}
	

	@GetMapping("/nuevo")
	public String crearUser(Model modelo) {
		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		
		return "crear_user";
	}
	
	
	@PostMapping("/save")
	public String saveUser(Usuario user, Model model, @RequestParam("file") MultipartFile imagen) {

		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static///assets//img//imagenesPerfiles");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {
				model.addAttribute("user", user);

				String contra  = user.getPassword();

				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(contra);
				user.setPassword(hashedPassword);

				user.setImg(imagen.getOriginalFilename());
				Usuario usuario = service.save(user);



				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + usuario.getId() + "-" +imagen.getOriginalFilename());
				java.nio.file.Files.write(rutaCompleta, bytesImg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/usuario/perfil";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/editar/{id}")
	public String mostarFormEditar(@PathVariable String id, Model modelo, Usuario usuario) {
		//Usuario user = service.getUserId(rut);
		//modelo.addAttribute("usuario", user);
		modelo.addAttribute("usuario", service.getUserId(id));
		return "editar_user";
		
	}

	@Secured("ROLE_USER")
	@PostMapping("/user/{id}")
	public String actualizarUser(@PathVariable String id, @ModelAttribute("usuario") Usuario usuario, Model modelo) {
		Usuario usuarioExistente = service.getUserId(id);
		usuarioExistente.setId(id);
		usuarioExistente.setNombre(usuario.getNombre());
		usuarioExistente.setApellido1(usuario.getApellido1());
		usuarioExistente.setApellido2(usuario.getApellido2());
		usuarioExistente.setNacimiento(usuario.getNacimiento());
		usuarioExistente.setPassword(usuario.getPassword());
		usuarioExistente.setImg(usuario.getImg());

		usuarioExistente.setTelefono(usuario.getTelefono());
		
		service.actualizarUser(usuarioExistente);
		return "redirect:/usuario/perfil";
		
	}
	@Secured("ROLE_USER")
	@GetMapping("/eliminar/{id}")
	public String eliminarUser(@PathVariable String id) {
		service.eliminarEstudiante(id);
		return "redirect:/residencia";
	}
	
	
	
}
