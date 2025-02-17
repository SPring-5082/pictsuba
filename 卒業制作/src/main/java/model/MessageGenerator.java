package model;

import java.net.URLEncoder;

public class MessageGenerator {
	private static String MESSAGE_FORMAT =
		 "nameさん\n"
		+ "いつもapplicationをご利用いただきありがとうございます\n"
		+ "■パスワードを再設定する場合\n"
		+ "以下のURLからパスワードの再設定を行えます\n"
		+ "このURLは24時間のみ有効です\n"
		+ "https://domain/reset-pass?token=user_token";
	public static String generate(String name,String domain, String application, String token) {
		String message =  MESSAGE_FORMAT.replaceFirst("name", name);
		message = message.replaceFirst("domain", domain);
		message = message.replaceAll("application", application);
		message = message.replaceFirst("user_token", URLEncoder.encode(token));
		return message;
	}
	
	public static String generate(String domain, String application, String token) {
		return 
		"アカウント作成用URL\n"+
		"https://" + domain + application + "/" + "signup/?token=" + token;
		
	}
	
}
