package com.example.demo.service;

import java.util.List;

import org.jeasy.rules.api.Facts;
import org.springframework.stereotype.Service;

import com.example.demo.constants.ProductConstants;
import com.example.demo.model.Product;
import com.example.demo.rules.RuleEngine;

@Service
public class ProductPromotionService {
	
	Double totalPrice=0D;
	
	public Double getTotalPriceOfCart(List<Product> products) {
		for(Product product : products) {
			if(product.getId().equals(ProductConstants.productA)) {
				Facts fact = new Facts(); 
				fact.put(ProductConstants.productAPromotion, product);
				RuleEngine.getRulesEngineInstance().fire(RuleEngine.getRuleInstance(), fact);
			}
			if(product.getId().equals(ProductConstants.productB)) {
				Facts fact = new Facts(); 
				fact.put(ProductConstants.productBPromotion, product);
				RuleEngine.getRulesEngineInstance().fire(RuleEngine.getRuleInstance(), fact);
			}
		}
		Facts fact = new Facts(); 
		fact.put(ProductConstants.productCAndDPromotion, products);
		RuleEngine.getRulesEngineInstance().fire(RuleEngine.getRuleInstance(), fact);
		
		
		products.forEach( product ->{
			totalPrice = totalPrice + product.getPrice();
		});
		
		return totalPrice;
	}
	
	
}
