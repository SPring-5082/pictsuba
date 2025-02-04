package model;

import java.util.Properties;

import beans.Mail;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailLogic {
	/**SMTPサーバーアドレス(ドメイン名)*/
	private static final String host = "smtp.gmail.com";
	/**宛先ポート番号*/
	private static final String port = "587";
	/**
	 * 送信元メールアドレス
	 * アプリケーションからのメールのため固定
	 */
	public static final String src_address = System.getProperty("src_add");
	/**アプリパスワード*/
	private static final String password = System.getProperty("mail_pass");
	/**プロパティの設定*/
	private static final Properties PROPERTIES = new Properties();
	private static Authenticator auth= null;
	private static Session session = null;
	static {
		PROPERTIES.put("mail.smtp.host", host);
		PROPERTIES.put("mail.smtp.port", port);
		PROPERTIES.put("mail.smtp.auth", "true");
		PROPERTIES.put("mail.smtp.starttls.enable", "true");		
		// セッションの作成
		auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(src_address, password);
			}
		};
		session = Session.getInstance(PROPERTIES,auth);
		
	}
	/**
	 * staticブロック内のセッション初期化処理を行うためのメソッド
	 */
	public static void init() {}
	
	/**
	 * 取得した情報をもとにパスワード変更ページのメール送信を行う
	 * @param mail メール情報
	 * @return 実行の成否
	 */
	public static boolean execute(Mail mail) {
		// メールの情報
		final String dist = mail.dist_address();
		final String title = mail.title();
		final String mainText = mail.mainText();
		
		try {
			// メールの作成
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(src_address));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dist));
			message.setSubject(title);
			message.setText(mainText);
			// メールの送信
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			init();
			return false;
		}
	}
	
}
