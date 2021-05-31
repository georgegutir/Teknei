package com.teknei.practicas.concesionario.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.teknei.practicas.concesionario.entidades.Coches;
import com.teknei.practicas.concesionario.entidades.Marcas;
import com.teknei.practicas.concesionario.repositorios.CocheRepositorio;
import com.teknei.practicas.concesionario.repositorios.MarcaRepositorio;

@Controller
@RequestMapping("/")
public class IndexController {
	
	static final Logger LOG = Logger.getLogger("FileNetUtil.class");
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
		mp.put("marcas", mar.findById(marca_id));
		
		Iterable<Coches> cochesPorMarca = car.findByMarca(marca_id);
		mp.addAttribute("coches", cochesPorMarca);
		crearPDF(cochesPorMarca);
		
		return "lista";
	}
	
	public void crearPDF(Iterable<Coches> coches) {

    	Document documento = new Document();
    	Date date = new Date();
    	String fecha = new SimpleDateFormat("yyyyMMdd").format(date);
        
    	try {
    		String FILE_NAME = "C:/Users/JORGE/git/Teknei/concesionario/src/main/resources/pdf/" + fecha + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(new File(FILE_NAME)));

            documento.open();

            documento.add(new Paragraph("Fichero " + fecha + ".pdf"));
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(2);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla.addCell("Modelo");
            tabla.addCell("Matrícula");
            for (Coches coche:coches) {
            	tabla.addCell(coche.getModelo());
            	tabla.addCell(coche.getMatricula());
            }

            documento.add(tabla);

            documento.close();


        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}

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
