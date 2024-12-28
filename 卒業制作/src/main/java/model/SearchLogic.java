package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Product;
import dao.ProductDAO;

/**
 * 商品検索ロジッククラス
 */
public class SearchLogic {
	
	public static List<Product> execute(String searchWord, String order) throws SQLException {
		List<Product> products = ProductDAO.findBySearchWord(searchWord);
		
		switch (order) {
			case "POPULARITY":
				products.sort(ProductComparator.DEFAULT.comparator());
				break;
			case "PRICE_ASC":
				products.sort(ProductComparator.PRICE_ASC.comparator());
				break;
			case "PRICE_DESC":
				products.sort(ProductComparator.PRICE_DESC.comparator());
				break;
			case "NEWER":
				products.sort(ProductComparator.NEWER.comparator());
				break;
			default:
				products = sort(products,searchWord);
				break;
		}
		return products;
	}
	
	/**
	 * 検索結果リストを完全一致するものの表示優先度を上げる
	 * @param products 検索語を含む商品リスト
	 * @param seachWord 検索語
	 * @return 更新済みリスト
	 */
	private static List<Product> sort(List<Product> products, String seachWord) {
		List<Product> priority = new ArrayList<Product>();
		List<Product> posteriority = new ArrayList<Product>();
		
		for(Product product : products) {
			if(equals(product,seachWord)) {
				priority.add(product);
			}else {
				posteriority.add(product);
			}
		}
		priority.sort(ProductComparator.DEFAULT.comparator());
		posteriority.sort(ProductComparator.DEFAULT.comparator());
		products = new ArrayList<Product>();
		for(Product product : priority) {
			products.add(product);
		}
		for(Product product : posteriority) {
			products.add(product);
		}
		return products;
	}
	
	/**
	 * 商品が検索語と完全に一致しているか検査するメソッド
	 * @param product 検査対象商品
	 * @param searchWord 検索語
	 * @return 一致の真偽
	 */
	private static boolean equals(Product product, String searchWord) {
		if(product.name().equals(searchWord))return true;
		if(product.creator_name().equals(searchWord))return true;
		if(product.category_name().equals(searchWord))return true;
		return false;
	}
	
}
