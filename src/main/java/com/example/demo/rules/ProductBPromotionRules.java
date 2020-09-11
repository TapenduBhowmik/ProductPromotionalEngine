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
public class ProductBPromotionRules {
	@Autowired
	ProductPriceService productService;
	
	@Condition
	public boolean productBFirstRule(@Fact("2 B's for 45") Product product) {
		if(ProductConstants.productB.equals(product.getId())) {
				return true;
		}
		return false;
	}
	
	@Action
	public void getProductBFirstRule(@Fact("2 B's for 45") Product product) {
		
		Double unitPrice = productService.getUnitProductPrice(ProductConstants.productB);
		Double price = 0D;
		Integer offeredQuantity = product.getQuantity()/2;
		Integer nonOfferedQuanity = product.getQuantity() % 2;
		price = offeredQuantity*45D + unitPrice*nonOfferedQuanity;
		product.setPrice(price);
	}

}
