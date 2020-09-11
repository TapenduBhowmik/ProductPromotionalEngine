package com.example.demo.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.constants.ProductConstants;
import com.example.demo.model.Product;
import com.example.demo.service.ProductPriceService;

@Rule
public class ProductAPromotionRules {
	
	@Autowired
	ProductPriceService productService;
	@Condition
	public boolean productAFirstRule(@Fact("3 A's for 130") Product product) {
		if(ProductConstants.productA.equals(product.getId())) {
				return true;
		}
		return false;
	}
	
	@Action
	public void getProductAFirstRule(@Fact("3 A's for 130") Product product) {
		
		Double unitPrice = 50D;// productService.getUnitProductPrice(ProductConstants.productA);
		Double price = 0D;
		Integer offeredQuantity = product.getQuantity()/3;
		Integer nonOfferedQuanity = product.getQuantity() % 3;
		price = offeredQuantity*130D + unitPrice*nonOfferedQuanity;
		product.setPrice(price);
	}
	
}
