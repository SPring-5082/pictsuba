package beans;

import model.MailLogic;

public record Mail(String src_addres,String dist_address,String title,String mainText){
	public Mail(String dist_address,String title,String mainText){
		this(MailLogic.src_address,dist_address,title,mainText);
	}
}
