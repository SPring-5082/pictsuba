package test.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import beans.Order_History;
import dao.Order_HistoryDAO;

class TestOrder_HistoryDAO {
	/*
	@Test
	void testInsert(){
		try {
			Order order = new Order(6, 1, 3, 1, 500, 1,new Date(), "済");
			boolean b = Order_HistoryDAO.insert(order);
			assertEquals(true, b);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	@Test
	void testFindByOrder_History_id() {
		try {
			List<Order_History> list = Order_HistoryDAO.findByOrder_History_id(1);
			for(Order_History oh : list) {
				System.out.println(oh.product_id());
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@Test
	void testFindByCustomr_id() {
		try {
			List<Order_History> list = Order_HistoryDAO.findByCustomr_id(6);
			for(Order_History oh : list) {
				System.out.println(oh.product_id());
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
