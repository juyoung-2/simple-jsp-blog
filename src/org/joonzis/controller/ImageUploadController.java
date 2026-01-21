package org.joonzis.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/ImageUploadController")
public class ImageUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ImageUploadController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String realPath = request.getServletContext().getRealPath("/upload/editor");
		File dir = new File(realPath);
		if (!dir.exists()) dir.mkdirs();

		MultipartRequest mr = new MultipartRequest(
			request,
			realPath,
			10 * 1024 * 1024,
			"utf-8",
			new DefaultFileRenamePolicy()
		);

		File file = mr.getFile("image");
		String fileName = file.getName();

		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().print("upload/editor/" + fileName);
	}

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
