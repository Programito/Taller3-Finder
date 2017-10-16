package com.book.app.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class ServletEditImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	/* referencia por inyección */
	@EJB
	private ImageService service; 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
           String messageResponse="";
		   String option = request.getParameter("option");  
		   
		   
		  
		   	   
		   if(option.equals("insert")){			  
		   		  
			   try{
				   Part filePart = request.getPart("imagefile");
				   String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
				   InputStream fileContent = filePart.getInputStream();
				   byte[] byteImage  =inputStreamToByte(fileContent);
				   
				   Image image = service.insert(byteImage); 
				   String url = "./server/image/" +  image.getUrl(); 
				   messageResponse="La imagen " +fileName+" se guardo correctamente: " + url;
				   
				   
			   		
			   }catch(Exception e){
				   response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				   return; 
			   }
			   
		   }else {			 
			   response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			   return; 
			   
		   }
		   
		
		   
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        out.println(HttpHelper.getStyleTable()); 
	        out.println("<title>Insert Image</title>");
	        out.println("</head>");
	        out.println("<body>");
	       
	        out.println("<p>");    
			out.println(messageResponse);
	        out.println("<p>");    
	        
	        out.println("<p>");    
	        	out.println("<a href=../index.html> Menu </a>");
	        out.println("<p>");
	        out.println("</body>");
	        out.println("</html>");
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