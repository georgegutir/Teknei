package com.teknei.practicas.concesionario.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teknei.practicas.concesionario.entidades.Coches;
import com.teknei.practicas.concesionario.entidades.Marcas;
import com.teknei.practicas.concesionario.repositorios.CocheRepositorio;
import com.teknei.practicas.concesionario.repositorios.MarcaRepositorio;

@RestController
@RequestMapping("/api")
public class IndexApiController {
	@Autowired
	private CocheRepositorio car;
	@Autowired
	private MarcaRepositorio mar;
	
	@PostMapping("/alta")
	public Coches alta(Coches nuevoCoche) {
	    return car.save(nuevoCoche);
	}
	
	@GetMapping("/marcas")
	public Iterable<Marcas> marcas() {
		return mar.findAll();
	}
	
	@GetMapping("/listado")
	public Iterable<Coches> listado() {
		return car.findAll();
	}
	
	@GetMapping("/lista")
	public Iterable<Coches> lista(@RequestParam(value="marca_id") Long marca_id) {
		return car.findByMarca(marca_id);
	}
}
