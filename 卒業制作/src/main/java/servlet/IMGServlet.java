package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DirectoryLogic;

@WebServlet("/image")
public class IMGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("file");
		final String image = request.getParameter("image");
		if(image != null && DirectoryLogic.safety(image)) {
			final String root =  System.getProperty("path");
			final String path = root +"/"+ image;
			try(FileInputStream fis = new FileInputStream(path);
				OutputStream os = response.getOutputStream()){
				os.write(fis.readAllBytes());
				fis.close();
				os.close();
				return;
			}catch (Exception e) {}
		}
		response.setStatus(response.SC_NOT_FOUND);
		return;
	}

}
