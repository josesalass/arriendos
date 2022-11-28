package com.example.arriendos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.arriendos.model.Usuario;
import com.example.arriendos.services.UsuarioService;
import com.example.arriendos.services.Impl.UsuarioServiceImpl;

import jakarta.validation.Valid;

@RequestMapping("/usuario")
@Controller
public class UsuarioController {
	
	@Autowired 
	UsuarioService service;
	
	@GetMapping("user")
	public String getUser(Model model, String rut) {
		rut = "20077281-4";
		Usuario user = service.getById(rut);
		
		
		model.addAttribute("usuario",user);
		
		return "Perfil";
	}
	
	
	@PostMapping("/save")
	public String saveUser(@Valid Usuario user, Model model) {
		service.save(user);
		
		return "Perfil";
	}
}
