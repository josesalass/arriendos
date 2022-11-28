package com.example.arriendos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class WelcomeController {
	
	@GetMapping("")
	public String index(Model model) {
		return "Piezas";
	}
}
