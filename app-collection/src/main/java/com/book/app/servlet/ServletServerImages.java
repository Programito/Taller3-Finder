package com.book.app.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.book.app.business.ImageService;

import entities.Image;


@MultipartConfig
public class ServletServerImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	/* referencia por inyección */
	@EJB
	private ImageService service; 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
          
		
		 
			  
           String pathInfo = request.getPathInfo(); // /{value}/test
           String imageName = pathInfo.replace("/", ""); 
           
           Image image = service.find(imageName); 
           
           if(image!=null){
        	   response.setContentLength(image.getBytes().length); 
	      	   response.setContentType("image/jpg");
		       OutputStream out = response.getOutputStream();
		       out.write(image.getBytes());
		       out.close();
           }else {
        	   response.sendError(HttpServletResponse.SC_NOT_FOUND);
           }
		
		
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
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