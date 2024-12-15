package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Product;

/**
 * 商品検索ロジッククラス
 */
public class SearchResultLogic {
	/**
	 * 実行メソッド
	 * @param productList 商品一覧
	 * @param searchWord 検索ワード
	 * @return 検索結果
	 */
	public static List<Product> execute(List<Product> productList, String searchWord) {
		
		Map<Product, Integer> scoreMap = new HashMap<Product, Integer>();
		int score = 0;
		
		for(Product product : productList)
		{
			if(product.name() == searchWord || product.creator_name() == searchWord)
			{
				score += 10;
			} 
			else if(product.category_name() == searchWord ||
						product.name().contains(searchWord) ||
						product.creator_name().contains(searchWord))
			{	
				score += 5;	
			}
			
			score += (product.lookup()/2) + product.sales_quantity();
			
			scoreMap.put(product, score);
			score = 0;
		}
		
		//scoreの降順に並び替え
		List<Map.Entry<Product, Integer>> entryList = new ArrayList<>(scoreMap.entrySet());
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        List<Product> responsProductList = new ArrayList<Product>();

        for(Map.Entry<Product, Integer> entry : entryList) {
        	responsProductList.add(entry.getKey());
        }
		
		return responsProductList;
	}
}
