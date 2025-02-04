package listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener("/*")
public class RequestCountListener implements ServletRequestListener {
	
	public void requestInitialized(ServletRequestEvent sre)  { 
		BootListener.reqCount += 1;
	}
	
}
