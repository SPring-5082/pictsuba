package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.CampaignLogic;
import model.KeyStorage;
import model.MailLogic;

@WebListener
public class BootListener implements ServletContextListener {
	/**
	 * サーバ起動時に
	 * このアプリケーションの名前と
	 * このサーバのドメイン名を
	 * アプリケーションスコープに設定し、
	 * 暗号化鍵の読み込みを行う
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce)  {
		sce.getServletContext().getContextPath();
		ServletContext sc =  sce.getServletContext();
		//ドメイン名設定
		final String domain = "localhost:8080";
		sc.setAttribute("domain", domain);
		//アプリケーション名設定
		final String application = sc.getContextPath();
		sc.setAttribute("application", application.replaceFirst("/", ""));
		
		//暗号鍵の読み込み
		KeyStorage.init("O52HO0G5C3NtkegLeQE0Kg==", "jxCxl18YNgUEY97WEhbE/Q==");
		
		//メールサーバとのセッション確立
		MailLogic.init();
		
		//キャンペーン価格計算機設定
		sc.setAttribute("calc", new CampaignLogic());
		
	}
	
}
