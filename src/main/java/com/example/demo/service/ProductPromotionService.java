package com.example.demo.service;

import java.util.List;

import org.jeasy.rules.api.Facts;
import org.springframework.stereotype.Service;

import com.example.demo.constants.ProductConstants;
import com.example.demo.model.Product;
import com.example.demo.rules.RuleEngine;

@Service
public class ProductPromotionService {
	
	public void getTotalPriceOfCart(List<Product> products) {
		for(Product product : products) {
			if(product.getId().equals(ProductConstants.productA)) {
				Facts fact = new Facts(); 
				fact.put("3 A's for 130", product);
				RuleEngine.getRulesEngineInstance().fire(RuleEngine.getRuleInstance(), fact);
			}
		}
	}
}
