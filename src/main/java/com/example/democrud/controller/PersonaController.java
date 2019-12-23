package com.example.democrud.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.democrud.model.Persona;
import com.example.democrud.service.api.PersonaServiceAPI;

@Controller
public class PersonaController {

	@Autowired
	private PersonaServiceAPI personaServiceAPI;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("list", personaServiceAPI.getAll());
		return "index";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model) {
		if (id != null && id != 0) {
			model.addAttribute("persona", personaServiceAPI.get(id));
		} else {
			model.addAttribute("persona", new Persona());
		}
		return "save";
	}

	@PostMapping("/save")
	public String save(Persona persona, Model model) {
		personaServiceAPI.save(persona);
		return "redirect:/";
	}

	@GetMapping("/export/all")
	public ResponseEntity<InputStreamResource> exportAllData() throws Exception {
		ByteArrayInputStream stream = personaServiceAPI.exportAllData();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=personas.xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		personaServiceAPI.delete(id);

		return "redirect:/";
	}

}
