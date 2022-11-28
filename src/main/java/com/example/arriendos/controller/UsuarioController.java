package com.example.arriendos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.arriendos.model.Usuario;
import com.example.arriendos.services.UsuarioServiceImpl;

@RequestMapping("/usuario")
@Controller
public class UsuarioController {
	
	@Autowired 
	UsuarioServiceImpl service;
	
	@GetMapping("/user")
	public String getUser(Model model, String rut) {
		Usuario user = service.getById("rut");
		model.addAttribute("usuario",user);
		
		return "Perfil";
	}
	
}
