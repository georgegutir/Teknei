package com.teknei.practicas.concesionario.repositorios;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.teknei.practicas.concesionario.entidades.Coches;
import com.teknei.practicas.concesionario.entidades.Marcas;

public class MarcaDaoJdbcTemplate implements MarcaDao{

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Iterable<Marcas> obtenerTodos(){
		return jdbc.query("SELECT * FROM marcas", new BeanPropertyRowMapper<Marcas>(Marcas.class));
	}
	
	@Override
	public Marcas obtenerPorId(Long id) {
		return jdbc.queryForObject("SELECT * FROM marcas WHERE id = ?", new BeanPropertyRowMapper<Marcas>(Marcas.class), id);
	}
	
	@Override
	public Marcas obtenerPorIdConCoches(Long id) {
		Marcas marcas = obtenerPorId(id);
		Collection<Coches> coches = jdbc.query(
				"SELECT c.id, c.modelo, c.matricula FROM coches c LEFT JOIN marcas m ON m.id = c.marca_id WHERE m.id = ?",
				new BeanPropertyRowMapper<Coches>(Coches.class), id);
		marcas.getCoches().addAll(coches);
		
		return marcas;
	}

}
