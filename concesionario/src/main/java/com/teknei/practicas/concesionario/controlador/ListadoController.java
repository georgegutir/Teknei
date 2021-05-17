package com.teknei.practicas.concesionario.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListadoController {

	@RequestMapping("/listado")
	public String get() { 
		return "listado";
	}
}
