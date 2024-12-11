package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Product;

public class SearchResultLogic {
	public static List<Product> execute(List<Product> productList, String searchWord) {
		
		Map<Integer, Product> scoreMap = new HashMap<Integer, Product>();
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
			
			scoreMap.put(score, product);
		}
		
		Object[] mapkey = scoreMap.keySet().toArray();
        Arrays.sort(mapkey);
        
        List<Product> responsProductList = new ArrayList<Product>();
        for (Integer nKey : scoreMap.keySet())
        {
            responsProductList.add(scoreMap.get(nKey));
        }
		
		return responsProductList;
	}
}
