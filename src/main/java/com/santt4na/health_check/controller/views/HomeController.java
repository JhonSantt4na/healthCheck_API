package com.santt4na.health_check.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("pageTitle", "Home");
		model.addAttribute("pageCss", "/css/home.css");
		model.addAttribute("pageContent", "home");
		model.addAttribute("mensagem", "Olá, mundo! Este é um exemplo com Thymeleaf.");
		return "layouts/fragments";
	}
	
	@GetMapping("/consultas")
	public String consultas(Model model) {
		model.addAttribute("pageTitle", "Minhas Consultas");
		model.addAttribute("pageCss", "/css/consultas.css");
		model.addAttribute("pageContent", "consultas");
		model.addAttribute("consultas", java.util.Arrays.asList(
			new Consulta("Consulta com Cardiologista - 01/07/2025"),
			new Consulta("Consulta com Pediatra - 05/07/2025")
		));
		return "layouts/fragments";
	}
	
	static class Consulta {
		private String descricao;
		public Consulta(String descricao) {
			this.descricao = descricao;
		}
		public String getDescricao() {
			return descricao;
		}
	}
}