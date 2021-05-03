package br.com.alexsandro.project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alexsandro.project.dto.UsuarioDTO;
import br.com.alexsandro.project.entities.Sexo;
import br.com.alexsandro.project.services.UsuarioService;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService service;

	@GetMapping("")
	public ModelAndView index() {

		List<UsuarioDTO> usuarios = new ArrayList<>();
		ModelAndView mv = new ModelAndView("usuarios/UserList");
		try {
			usuarios = service.findAll();
			mv.addObject("usuarios", usuarios);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	@GetMapping(value = "/new")
	public ModelAndView newUser(UsuarioDTO dto) {
		ModelAndView mv = new ModelAndView("usuarios/user_new");

		mv.addObject("sexo", Sexo.values());
		return mv;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView insert(@Valid UsuarioDTO dto, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView().addObject("sexo", Sexo.values());

		if (bindingResult.hasErrors()) {
			mv.setStatus(HttpStatus.BAD_REQUEST);
			mv.setViewName("usuarios/user_new");
			return mv;

		} else {
			UsuarioDTO usuario = service.insert(dto);
			mv.setStatus(HttpStatus.CREATED);
			mv.setViewName("redirect:/usuarios/" + usuario.getId().toString());
			return mv;
		}

	}

	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		UsuarioDTO dto = service.findById(id);

		if (!dto.getErros().isEmpty()) {
			return new ModelAndView("redirect:/usuarios");
		} else {
			ModelAndView mv = new ModelAndView("usuarios/show");
			mv.addObject("usuario", dto);
			return mv;
		}

	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, UsuarioDTO request) {
		ModelAndView mv = new ModelAndView("usuarios/user_edit");
		UsuarioDTO usuario = service.findById(id);
		mv.addObject("usuarioDTO", usuario).addObject("sexo", Sexo.values());
		;

		return mv;
	}

	@PatchMapping("/{id}/edit")
	public ModelAndView editUser(@PathVariable Long id, UsuarioDTO request) {
		ModelAndView mv = new ModelAndView("usuarios/show");
		UsuarioDTO dto = service.edit(id, request);
		mv.addObject("usuario", dto);
		return mv;
	}
	
	@DeleteMapping("/{id}/delete")
	public ModelAndView delete(Long id, UsuarioDTO dto) {
		ModelAndView mv = new ModelAndView("usuarios/");
		service.deleteById(id, dto);
		
		return mv;
	}
	

}
