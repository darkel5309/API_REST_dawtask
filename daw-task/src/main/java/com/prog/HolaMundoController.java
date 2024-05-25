package com.prog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holamundo")
public class HolaMundoController {

	@GetMapping("/hola")
	public String saludar() {
		return "Holaaaa";
	}
}
