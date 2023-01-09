package com.example.arriendos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String saveUser(Usuario user, Model model) {
		service.save(user);
		
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
