package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeasy.rules.api.Facts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.ProductConstants;
import com.example.demo.model.Product;
import com.example.demo.rules.RuleEngine;

@Service
public class ProductPromotionService {
	
	Logger logger = LoggerFactory.getLogger(ProductPromotionService.class);
	@Autowired
	ProductPriceService price;
	
	
	static Map<String,String> individualProductPromotion = new HashMap<String,String>();
	
	static {
		individualProductPromotion.put(ProductConstants.productA, ProductConstants.productAPromotion);
		individualProductPromotion.put(ProductConstants.productB, ProductConstants.productBPromotion);
	}
	
	
	public Double getTotalPriceOfCart(List<Product> products) {
		Double totalPrice=0D;
		Map<String,Double> unitPriceMap = price.init();
		for(Product product : products) {
			product.setUnitPrice(unitPriceMap.get(product.getId()));
			if(individualProductPromotion.containsKey(product.getId())) {
				Facts fact = new Facts(); 
				fact.put(individualProductPromotion.get(product.getId()), product);
				RuleEngine.getRulesEngineInstance().fire(RuleEngine.getRuleInstance(), fact);
			}
		}
		this.getCombinedPromotion(products);
		for(Product product : products) {
			totalPrice = totalPrice + product.getPrice();
		}
		logger.info("Total price is" + totalPrice);
		return totalPrice;
	}
	
	
	private List<Product> getCombinedPromotion(List<Product> products){
		Facts fact = new Facts(); 
		fact.put(ProductConstants.productCAndDPromotion, products);
		RuleEngine.getRulesEngineInstance().fire(RuleEngine.getRuleInstance(), fact);
		return products;
	}
}
