package com.teknei.practicas.concesionario.repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.teknei.practicas.concesionario.entidades.Coches;
import com.teknei.practicas.concesionario.entidades.Marcas;

public class CocheDaoJdbcTemplate implements Dao<Coches>{
	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Iterable<Coches> obtenerTodos() {
		return jdbc.query("SELECT * FROM coches c LEFT JOIN marcas m ON c.marca_id = m.id", (rs, rowNum) -> 
				new Coches(rs.getLong("c.id"), rs.getString("c.modelo"), rs.getString("c.matricula"),
						new Marcas(rs.getLong("m.id"), rs.getString("m.marca"))));
	}
	
	
	@Override
	public Coches obtenerPorId(Long id) {
		return jdbc.queryForObject("SELECT * FROM coches c LEFT JOIN marcas m ON c.marca_id = m.id WHERE c.id = ?", (rs, rowNum) ->
				new Coches(rs.getLong("c.id"), rs.getString("c.modelo"), rs.getString("c.matricula"),
						new Marcas(rs.getLong("m.id"), rs.getString("m.marca"))), id);	
		
	}
}
