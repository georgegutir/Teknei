package com.teknei.practicas.concesionario.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public ModelAndView altaCoche() { 
		return new ModelAndView("index");
	}
	

}
