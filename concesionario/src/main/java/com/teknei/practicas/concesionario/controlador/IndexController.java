package com.teknei.practicas.concesionario.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teknei.practicas.concesionario.entidades.Coches;
import com.teknei.practicas.concesionario.entidades.Marcas;
import com.teknei.practicas.concesionario.repositorios.CocheRepositorio;
import com.teknei.practicas.concesionario.repositorios.MarcaRepositorio;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private CocheRepositorio car;
	@Autowired
	private MarcaRepositorio mar;
	
	@PostMapping("/alta")
	public Coches alta(Coches nuevoCoche) {
	    return car.save(nuevoCoche);
	}
	
	@GetMapping("/index")
	public String get() { //doGet
		return "index"; // request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request,response)
	}	
	
	@GetMapping("/marcas")
	public Iterable<Marcas> marcas() {
		return mar.findAll();
	}
	
	@GetMapping("/listado")
	public String listado(ModelMap mp){
		mp.put("coches", car.findAll());
		//mp.put("Marcas", mar.findById(id));
		return "listado";
	}
	
	@GetMapping("/lista")
	public String lista(@RequestParam(value="marca_id") Long marca_id, ModelMap mp) {
		mp.put("coches", car.findByMarca(marca_id));
		return "lista";
	}
	
	//@GetMapping("/lista")
	//public Iterable<Coches> lista(@RequestParam(value="marca_id") Long marca_id) {
		//return car.findByMarca(marca_id);
	//}
	
	/**@PostMapping("/alta")
	public String alta(Coches coches) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				) {
			ps.setLong(1, coches.getMarcas().getId());
			ps.setString(2, coches.getModelo());
			ps.setString(3, coches.getMatricula());
			
			int num = ps.executeUpdate();
			
			if(num != 1) {
				throw new AccesoDatosException("Ha habido una incidencia en la inserción del coche: " + num);
			}
			
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			
			coches.setId(rs.getLong(1));
			
			con.commit();
			log.info(coches.toString());
			return "index";
		} catch (Exception e) {
			throw new AccesoDatosException("Error al insertar el coche " + coches, e);
		}
	} */	


}
