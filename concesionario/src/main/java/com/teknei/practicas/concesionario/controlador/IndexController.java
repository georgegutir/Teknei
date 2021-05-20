package com.teknei.practicas.concesionario.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teknei.practicas.concesionario.repositorios.AccesoDatosException;
import com.teknei.practicas.concesionario.entidades.Coches;
import com.teknei.practicas.concesionario.repositorios.CocheRepositorio;
import com.teknei.practicas.concesionario.repositorios.Dao;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	CocheRepositorio car;
	
	private static final String SQL_INSERT = "INSERT INTO Coches (marca_id, modelo, matricula) VALUES (?, ?, ?)";

	@Autowired
	private DataSource dataSource;

	@PostMapping("/alta")
	public String alta(Coches coches) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				) {
			ps.setLong(1, coches.getMarcas().getId());
			ps.setString(2, coches.getModelo());
			ps.setString(3, coches.getMatricula());
			
			int num = ps.executeUpdate();
			
			if(num != 1) {
				throw new AccesoDatosException("Ha habido una incidencia en la inserción de cliente: " + num);
			}
			
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			
			coches.setId(rs.getLong(1));
			
			con.commit();
			log.info(coches.toString());
			return "index";
		} catch (Exception e) {
			throw new AccesoDatosException("Error al insertar el cliente " + coches, e);
		}

	}
	
	
	//@RequestMapping(value = "listado", method = RequestMethod.POST)
	//public String listado(Model modelo) {
	//	modelo.addAttribute("coches", daoCoches.obtenerTodos());
	//	System.out.println(daoCoches.obtenerTodos());
	//	return "listado";
	//}	

	@GetMapping("/index")
	public String get() { //doGet
		return "index"; // request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request,response)
	}
	
	//@RequestMapping("/listado")
	//public String listado(Model modelo) {
		//modelo.addAttribute("coches", daoCoches.obtenerTodos());
	//	return "listado";
	//}
	
	@RequestMapping(value = "listado", method = RequestMethod.GET)
	public String listado(ModelMap mp)
	  {
		mp.put("coches", car.findAll());
		log.info(car.toString());
		return "listado";
	  }

}
