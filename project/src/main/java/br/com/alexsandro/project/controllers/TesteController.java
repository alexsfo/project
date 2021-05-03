package br.com.alexsandro.project.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TesteController {
	
	
	@GetMapping(value = "/")
	public String hello(Model model) {
		model.addAttribute("nome", "Alex");
		return "index";
	}
	
	
	@GetMapping(value = "/alex")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("nome", "Alexsandrooo");
		return mv;
	}
	
	

}
