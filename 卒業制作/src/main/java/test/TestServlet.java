package test;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EncryptionLogic;
import model.TokenLogic;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		String h = TokenLogic.generate();
		String f = TokenLogic.generate();
		System.out.println(h);
		System.out.println(f);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String header = "WdYyBPWBA";
		String footer = "hGkw0kOSj";
		try(PrintWriter pw = response.getWriter();){
			String sid;
			for(int i = 0;i < 20;i ++) {
				sid = EncryptionLogic.enc(header + i + footer);
				pw.println(sid);
				pw.println(EncryptionLogic.dec(sid));
				pw.println();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
