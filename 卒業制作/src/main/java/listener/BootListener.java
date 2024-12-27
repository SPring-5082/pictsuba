package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class BootListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce)  { 
		ServletContext sc =  sce.getServletContext();
		sc.setAttribute("application", "pictsuba");
	}
	
	public void contextDestroyed(ServletContextEvent sce)  {
		
	}
	
}
