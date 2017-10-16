package com.book.app.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.book.app.business.AppServices;
import com.book.app.business.ImageService;
import com.book.app.business.InfAppServices;

import entities.Collection;
import entities.Image;
import entities.Item;
import entities.User;


@MultipartConfig
public class ServletCreateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	/* referencia por inyección */
	//@EJB
	//private  AppServices service; 
	
	@Inject
	private InfAppServices services; 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
          
		
		User user = HttpHelper.getSessionUser(request); 
		if(user==null){
			response.sendRedirect("/app-book/index.html");
			return; 
		}

		
		String idCollection=request.getParameter("idCollection");

		if(idCollection==null){
			System.out.println("error al pasar el id de la colleccion");
			response.sendRedirect("/app-book/servlet/global");
		}
		else{
			String idItem=request.getParameter("idItem");
			String description = request.getParameter("description");
			String name = request.getParameter("name");
			//String image = request.getParameter("imagefile");
			byte[] bytesImage = conseguirImagen(request);  
			
			
			if(idItem==null && description!=null && name!=null){
				System.out.println("entre por create");
				Item item= new Item();
				item.setName(name);
				item.setDescription(description);
				try{				
						services.addItem(idCollection, item, bytesImage);
					    response.sendRedirect("/app-book/servlet/global");
					    
				}catch(EJBException e){
					System.out.println("error");
					response.sendRedirect("/app-book/servlet/global");
					return; 
				}
				
			}
			else if(idItem!=null  && description!=null && name!=null){
				System.out.println("entre por update");
				Item item= new Item();
				item.setName(name);
				item.setDescription(description);
				item.setId(idItem);
				try{				
						services.updateItem(item, bytesImage);
					    response.sendRedirect("/app-book/servlet/global");
					    
				}catch(EJBException e){
					System.out.println("error");
					response.sendRedirect("/app-book/servlet/global");
					return; 
				}
			}else if(idItem!=null){
				response.setContentType("text/html");
			
				PrintWriter out=response.getWriter();
			
				out.println(htmlUpdateCollection(idItem,idCollection));
			
			}else{
				response.setContentType("text/html");
			
				PrintWriter out=response.getWriter();
			
				out.println(htmlAddItem(idCollection));
			}
		}
		/*response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		out.println(htmlAddItem());*/
		
	}
	


	private void checkParameters() {
		// TODO Auto-generated method stub
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private String htmlAddItem(String idCollection){
		String salida="";
	
		
		salida="<!DOCTYPE html>"
		+ "<html>"
		    +"<head>"
		        + "<title>Nueva Coleccion</title>"
		        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
		   + " </head>"
			
			+ "<style>"

			+ "body{background-color:#FFC;}"
			+ "#principal{position: absolute;top:10%;left:10%;padding:10px 10px 10px 50px;background-color:#F93;box-shadow: 10px 10px 5px 0px #999999;border-radius: 10px;width: 80%;"
			+ "align:center;text-align: center;}"
			+ "h1{text-align:center;}"


			+ "</style>"
		   + "<body>"
				+"<div id=principal>"
		        + "<h1>Nuevo Item</h1>"
		        +"<img  onclick=validatorImagen() id='image_display' src='../img/banner_640x480.jpg' alt='Cargar image' style='width:320px;height:240px;'>"
		        + "<p id='p_info'>Seleccione una imagen de 640x480 formato jpg, y menor que 20KB</p>"
				+ "<form action='/app-book/servlet/ServletCreateItem' method='post' enctype='multipart/form-data'>"
		        	+ "<input id='imput_file_image' type='file' name='imagefile' value='Escojer Imagen'  accept='.jpg, .png' onchange='return validateAndSubmit()'>"
		        	+"<br>"
		        	+"<br>"
		        	+ "ID Collection: <input type='text'  name='idCollection' value='"+idCollection+"' readonly><br>"
					+ "Nombre: <input type='text' required='required' name='name'><br>"
					+ "Descripcion: <input type='text' required='required' name='description'><br>"
					+ "<input type='submit' value='Crear Item'>"
				+ "</form>"	
				+ "<br>"
				+ "<a href='/app-book/servlet/global'>Volver </a>"
				+ "</div>"
			+ "</body>"
			
			
			+ "<script>"
	        + "function validateAndSubmit() {"
	            + "var input, file;"
	      
	            + "document.getElementById('button_validate').disabled = true;"
	             
	             + "console.log('validate And submit'); "
	                
	            + "if (!window.FileReader) {"
	            	 + "pAppend('p_info', 'La API File reader no es soportada por este navegador');"
	                + "return;"
	            + "}"

	            + "input = document.getElementById('imput_file_image');"
	            
	         
	            + "if(input.files[0]){"
	                + "file = input.files[0];"
	                
	                + "if( file.size > 20000){"
	                	 + "pAppend('p_info','El archivo debe ser menor a 20KB, actualmente ' +  file.size + ' Bytes');"                  
	                      + "return;" 
	                + "}"
	                  
	                + "var image = document.getElementById('image_display');" 
	                + "image.src = window.URL.createObjectURL(file);"               
	                + "message =  'La imagen ' + file.name  + ' tiene ' + file.size + ' bytes, presione el boton [Subir image] para subir al servidor';" 
	                + "pAppend('p_info',message);"                  
	                + "console.log(message);"
	                + "document.getElementById('button_validate').disabled = false;"
	                
	            + "}else{"
	            	 + "pAppend('p_info', 'Error cargando la imagen');"
	            +"}"
	            
	            
	        + "}"

	        + "function  pAppend(tagName, innerHTML) {"
	           + " var elm= document.getElementById(tagName); "
	            + "elm.innerHTML = innerHTML;"
	        +"}"
				
			+"</script>"
		
		+ "</html>";
		
		
		return salida;
	}
	
	
	private String htmlUpdateCollection(String idItem,String idCollection){
		
	String salida="";
	
		
		salida="<!DOCTYPE html>"
		+ "<html>"
		    +"<head>"
		        + "<title>Nueva Coleccion</title>"
		        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
		   + " </head>"
			
			+ "<style>"

			+ "body{background-color:#FFC;}"
			+ "#principal{position: absolute;top:10%;left:10%;padding:10px 10px 10px 50px;background-color:#F93;box-shadow: 10px 10px 5px 0px #999999;border-radius: 10px;width: 80%;"
			+ "align:center;text-align: center;}"
			+ "h1{text-align:center;}"


			+ "</style>"
		   + "<body>"
				+"<div id=principal>"
		        + "<h1>Update Item</h1>"
		        +"<img  onclick=validatorImagen() id='image_display' src='../img/banner_640x480.jpg' alt='Cargar image' style='width:320px;height:240px;'>"
		        + "<p id='p_info'>Seleccione una imagen de 640x480 formato jpg, y menor que 20KB</p>"
				+ "<form action='/app-book/servlet/ServletCreateItem' method='post' enctype='multipart/form-data'>"
		        	+ "<input id='imput_file_image' type='file' name='imagefile' value='Escojer Imagen'  accept='.jpg, .png' onchange='return validateAndSubmit()'>"
		        	+"<br>"
		        	+"<br>"
		        	+ "ID Collection: <input type='text'  name='idCollection' value='"+idCollection+"' readonly><br>"
		        	+ "ID: <input type='text'  name='idItem' value='"+idItem+"' readonly><br>"
					+ "Nombre: <input type='text' required='required' name='name'><br>"
					+ "Descripcion: <input type='text' required='required' name='description'><br>"
					+ "<input type='submit' value='Update Item'>"
				+ "</form>"	
				+ "<br>"
				+ "<a href='/app-book/servlet/global'>Volver </a>"
				+ "</div>"
			+ "</body>"
			
			
			+ "<script>"
	        + "function validateAndSubmit() {"
	            + "var input, file;"
	      
	            + "document.getElementById('button_validate').disabled = true;"
	             
	             + "console.log('validate And submit'); "
	                
	            + "if (!window.FileReader) {"
	            	 + "pAppend('p_info', 'La API File reader no es soportada por este navegador');"
	                + "return;"
	            + "}"

	            + "input = document.getElementById('imput_file_image');"
	            
	         
	            + "if(input.files[0]){"
	                + "file = input.files[0];"
	                
	                + "if( file.size > 20000){"
	                	 + "pAppend('p_info','El archivo debe ser menor a 20KB, actualmente ' +  file.size + ' Bytes');"                  
	                      + "return;" 
	                + "}"
	                  
	                + "var image = document.getElementById('image_display');" 
	                + "image.src = window.URL.createObjectURL(file);"               
	                + "message =  'La imagen ' + file.name  + ' tiene ' + file.size + ' bytes, presione el boton [Subir image] para subir al servidor';" 
	                + "pAppend('p_info',message);"                  
	                + "console.log(message);"
	                + "document.getElementById('button_validate').disabled = false;"
	                
	            + "}else{"
	            	 + "pAppend('p_info', 'Error cargando la imagen');"
	            +"}"
	            
	            
	        + "}"

	        + "function  pAppend(tagName, innerHTML) {"
	           + " var elm= document.getElementById(tagName); "
	            + "elm.innerHTML = innerHTML;"
	        +"}"
				
			+"</script>"
		
		+ "</html>";
		
		
		return salida;
	
	}
	
	
	
	
	
	
	
	
	private String htmlUpdateCollection2(String idItem,String idCollection){
		
		String salida="";
	
		
		salida="<!DOCTYPE html>"
		+ "<html>"
		    +"<head>"
		        + "<title>Nueva Coleccion</title>"
		        + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
		   + " </head>"
			
			+ "<style>"

			+ "body{background-color:#FFC;}"
			+ "#principal{position: absolute;top:10%;left:10%;padding:10px 10px 10px 50px;background-color:#F93;box-shadow: 10px 10px 5px 0px #999999;border-radius: 10px;width: 80%;"
			+ "align:center;text-align: center;}"
			+ "h1{text-align:center;}"

			+ "</style>"
		   + "<body>"
				+"<div id=principal>"
		        + "<h1>Update Item</h1>"
				+ "<form action='/app-book/servlet/ServletCreateItem' method='post'>"
		        	+ "ID: <input type='text'  name='idColection' value='"+idItem+"' readonly><br>"
		        	+ "ID Collection: <input type='text'  name='idCollection' value='"+idCollection+"' readonly><br>"
		        		+"<img src='../img/anadir.png' align='left' alt='error al cargar imagen'>"
					+ "Nombre: <input type='text' required='required' name='name'><br>"
					+ "Descripcion: <input type='text' required='required' name='description'><br>"
					+"<button> cargar imagen </button>"
					+ "<input type='submit' value='Update Item'>"
				+ "</form>"	
				+ "<br>"
				+ "<a href='/app-book/servlet/global'>Volver </a>"
				+ "</div>"
			+ "</body>"
		+ "</html>";
		
		
		
		return salida;
	
	}
	
	private byte[] conseguirImagen(HttpServletRequest request){
		byte[] byteImage=null;
		
		try{
			  Part filePart = request.getPart("imagefile");
			  // String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			   InputStream fileContent = filePart.getInputStream();
			   byteImage  =inputStreamToByte(fileContent);
			   
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("error cargando imagen");
		}
		
		return byteImage;
	}
	
	
	private static byte[] inputStreamToByte(InputStream is){
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		try {
			while ((nRead = is.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
			
			buffer.flush();
		} catch (IOException e) {
			
		}

		return buffer.toByteArray();
	}
	

}

