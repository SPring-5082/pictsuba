package model;

import java.util.ArrayList;
import java.util.List;

import beans.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchResult {
	public static List<Product> execute(HttpServletRequest request, HttpServletResponse response) {
		//検索結果の取得
		List<Product> list = new ArrayList<Product>();
		return list;
	}
}
