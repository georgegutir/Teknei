package com.teknei.practicas.concesionario.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.filenet.api.admin.StorageArea;
import com.filenet.api.collection.ContentElementList;
import com.filenet.api.collection.DocumentSet;
import com.filenet.api.collection.ObjectStoreSet;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.ClassNames;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Factory.ContentTransfer;
import com.filenet.api.core.ReferentialContainmentRelationship;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.Id;
import com.filenet.api.util.UserContext;
import com.filenet.apiimpl.core.DocumentImpl;

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
	public String alta(Coches nuevoCoche) {
	    car.save(nuevoCoche);
	    return "index";
	}
	
	@GetMapping("/index")
	public String get() { //doGet
		return "index"; // request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request,response)
	}	
	
	public static Set<Marcas> getMarcas(Iterable<Coches> cochesPorMarca) {
		Set<Marcas> marcas = new HashSet<>();
		for (Coches coche : cochesPorMarca) {
			marcas.add(coche.getMarcas());
		}
		return marcas;
	}
	
	@GetMapping("/listado")
	public String listado(ModelMap mp){
		mp.put("coches", car.findAll());
		return "listado";
	}
	
	@GetMapping("/lista")
	public String lista(@RequestParam(value="marca_id") Long marca_id, ModelMap mp, Coches coche) {
		mp.put("coches", car.findByMarca(marca_id));
		Iterable<Coches> cochesPorMarca = car.findByMarca(marca_id);
		mp.addAttribute("coches", cochesPorMarca);
		Iterable<Marcas> marcas = mar.findAll();
		
		String marca;
		if (marca_id == 1) {
			marca = "Seat";
		} else if (marca_id == 2) {
			marca = "Renault";
		} else {
			marca = "Citroen";
		}
		
		crearPDF(cochesPorMarca, marca);
		getObjectStore(marcas);
		return "lista";
	}
	
	public void crearPDF(Iterable<Coches> coches, String marca) {
    	Document documento = new Document();
    	//Date date = new Date();
    	//String fecha = new SimpleDateFormat("yyyyMMdd").format(date);
        
    	try {
    		String FILE_NAME = "C:/Users/JORGE/git/Teknei/concesionario/src/main/resources/pdf/" + marca + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(new File(FILE_NAME)));

            documento.open();
            documento.add(new Paragraph("Documento: " + marca + ".pdf"));
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
	
	private static void getObjectStore(Iterable<Marcas> marcas) {
		Connection conn = Factory.Connection.getConnection("http://34.234.153.200/wsi/FNCEWS40MTOM");
		Subject subject = UserContext.createSubject(conn, "Jorge", "Hola1234$", "FileNetP8WSI");
		UserContext.get().pushSubject(subject);

		ObjectStore store = null;

		try {
			// Get default domain.
			Domain domain = Factory.Domain.fetchInstance(conn, null, null);
			LOG.info("Domain: " + domain.get_Name());

			// Get object stores for domain.
			ObjectStoreSet osSet = domain.get_ObjectStores();
			Iterator<ObjectStore> osIter = osSet.iterator();

			while (osIter.hasNext()) {
				store = osIter.next();
				LOG.info("Object store: " + store.get_Name());
				if (store.get_Name().equalsIgnoreCase("teknei")) {
					break;
				}
			}
		} finally {
			//borrarDocumentos(store);
			//crearDocumento(store, marcas);

			System.out.println("Connection to Content Platform Engine successful");
			UserContext.get().popSubject();
		}
	}
	
	/** 
	private static void crearDocumento(ObjectStore os, Iterable<Marcas> marcas) {
		
	        // Create a document instance.
			com.filenet.api.core.Document doc = Factory.Document.createInstance(os, "Coches");

	        // Set document properties.
	        doc.getProperties().putValue("DocumentTitle", "New Document via Java API");
	        doc.set_MimeType("application/pdf");
	        //StorageArea sa = Factory.StorageArea.getInstance(os, new Id("{DE42374D-B04B-4F47-A62E-CAC9AC9A5719}") );
			//doc.set_StorageArea(sa);
			
			// references to the document and file you are working with
			File file = new File("src/main/resources/pdf/Seat.pdf");
			try {
				com.filenet.api.core.ContentTransfer contentTransfer = Factory.ContentTransfer.createInstance();
				InputStream inputStream = new FileInputStream(file);
				ContentElementList contentList = Factory.ContentTransfer.createList();
				contentTransfer.setCaptureSource(inputStream);
				contentList.add(contentTransfer);

				doc.set_ContentElements(contentList);
				doc.save(RefreshMode.REFRESH);

				doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
				doc.save(RefreshMode.REFRESH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	        // File the document.
	        Folder folder = Factory.Folder.getInstance(os, ClassNames.FOLDER, new Id("{42A3FC29-D635-4C37-8C86-84BAC73FFA3F}")); // id of folder to which you want to store document.
	        ReferentialContainmentRelationship rcr = folder.file(doc, AutoUniqueName.AUTO_UNIQUE, "New Document via Java API",
	                DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
	        rcr.save(RefreshMode.NO_REFRESH);
	        
	}
	
	
	private static void crearDocumento(ObjectStore os, Marcas marca) {
		// Create a document instance.
		com.filenet.api.core.Document doc = Factory.Document.createInstance(os, "Coches");

		// Set document properties.
		doc.getProperties().putValue("DocumentTitle", marca.getMarca());
		doc.set_MimeType("application/pdf");

		// references to the document and file you are working with
		File file = new File("src/main/resources/pdf/" + marca.getMarca() + ".pdf");
		try {
			ContentTransfer contentTransfer = Factory.ContentTransfer.createInstance();
			InputStream inputStream = new FileInputStream(file);
			ContentElementList contentList = Factory.ContentTransfer.createList();
			contentTransfer.setCaptureSource(inputStream);
			contentList.add(contentTransfer);

			doc.set_ContentElements(contentList);
			doc.save(RefreshMode.REFRESH);

			doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
			doc.save(RefreshMode.REFRESH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// File the document.
		Folder folder = Factory.Folder.getInstance(os, ClassNames.FOLDER,
				new Id("{DE42374D-B04B-4F47-A62E-CAC9AC9A5719}"));
		ReferentialContainmentRelationship rcr = folder.file(doc, AutoUniqueName.AUTO_UNIQUE, marca.getMarca(),
				DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
		rcr.save(RefreshMode.NO_REFRESH);
	}

	  public static void crearDocumento(ObjectStore os, Marcas marca) {
	    	com.filenet.api.core.Document doc = Factory.Document.createInstance(os, "Coches");
	        // TO-DO: implement logic to populate document with the list of cars
	        doc.getProperties().putValue("documento", marca.getMarca());
	        doc.set_MimeType("application/pdf");
	        final StorageArea sa = Factory.StorageArea.getInstance(os, new Id("{DE42374D-B04B-4F47-A62E-CAC9AC9A5719}"));
	        doc.set_StorageArea(sa);
	        doc.save(RefreshMode.NO_REFRESH);
	        doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
	        doc.save(RefreshMode.NO_REFRESH);
	        final Folder folder = Factory.Folder.getInstance(os, ClassNames.FOLDER,
	                new Id("{42A3FC29-D635-4C37-8C86-84BAC73FFA3F}"));
	        final ReferentialContainmentRelationship rcr = folder.file(doc, AutoUniqueName.AUTO_UNIQUE,
	                "New Document via Java API", DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
	        rcr.save(RefreshMode.NO_REFRESH);
	    }**/
	
	private static void borrarDocumentos(ObjectStore os) {
		Folder folder = Factory.Folder.fetchInstance(os, "/concesionario", null);
		DocumentSet ds = folder.get_ContainedDocuments();
		
		Iterator i = ds.iterator();
		
		if (i.hasNext()) {
			while (i.hasNext()) {
				DocumentImpl doc = (DocumentImpl) i.next();
				com.filenet.api.core.Document docBorrar = Factory.Document.getInstance(os, "Coches", doc.get_Id());
				docBorrar.delete();
				docBorrar.save(RefreshMode.NO_REFRESH);
			}
		}
	}


}
