package com.example.demo.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.constants.ProductConstants;
import com.example.demo.model.Product;


@Rule
public class ProductBPromotionRules {
	Logger logger = LoggerFactory.getLogger(ProductBPromotionRules.class);
	@Condition
	public boolean productBFirstRule(@Fact("2 B's for 45") Product product) {
		if(ProductConstants.productB.equals(product.getId())) {
				return true;
		}
		return false;
	}
	
	@Action
	public void getProductBFirstRule(@Fact("2 B's for 45") Product product) {
		
		Double unitPrice = product.getUnitPrice();
		Double price = 0D;
		Integer offeredQuantity = product.getQuantity()/2;
		Integer nonOfferedQuanity = product.getQuantity() % 2;
		price = offeredQuantity*45D + unitPrice*nonOfferedQuanity;
		product.setPrice(price);
		
		logger.info("Total proce for product B" + price);
	}

}
