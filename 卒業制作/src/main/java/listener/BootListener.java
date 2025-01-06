package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.KeyStorage;

@WebListener
public class BootListener implements ServletContextListener {
	/**
	 * サーバ起動時にこのアプリケーションの
	 * 名前をアプリケーションスコープに設定し、
	 * 暗号化鍵の読み込みを行う
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce)  {
		//アプリケーション名設定
		sce.getServletContext().getContextPath();
		ServletContext sc =  sce.getServletContext();
		String application = sc.getContextPath();
		sc.setAttribute("application", application.replaceFirst("/", ""));
		
		//暗号鍵の読み込み
		KeyStorage.init("O52HO0G5C3NtkegLeQE0Kg==", "jxCxl18YNgUEY97WEhbE/Q==");
	}
	
}
