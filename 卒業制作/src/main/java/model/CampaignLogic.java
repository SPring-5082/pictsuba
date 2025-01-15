package model;

import java.io.Serializable;
import java.sql.SQLException;

import dao.CampaignDAO;
import exception.SQLDataNotFoundException;

public class CampaignLogic implements Serializable{
	
	public static int price(int price,int category_id) {
		try {
			if(CampaignDAO.exists(category_id)) {
				return (1-CampaignDAO.discount_rate(category_id)/100)*price;
			}else {
				throw new SQLDataNotFoundException();
			}
		} catch (SQLException | SQLDataNotFoundException e) {
			return price;
		}
	}
	
	public static int point(int price,int category_id) {
		try {
			if(CampaignDAO.exists(category_id)) {
				return price/50;
			}
		} catch (SQLException e) {}
		return price/100;
	}
	
}
