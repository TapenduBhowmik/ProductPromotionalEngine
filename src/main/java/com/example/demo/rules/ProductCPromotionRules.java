package com.example.demo.rules;

import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.constants.ProductConstants;
import com.example.demo.model.Product;
import com.example.demo.service.ProductPriceService;

@Rule
public class ProductCPromotionRules {
	@Autowired
	ProductPriceService productService;
	
	@Condition
	public boolean productCAndDFirstRule(@Fact("C & D for 45") List<Product> products) {
		for(Product product : products) {
			if(ProductConstants.productC.equals(product.getId()) || 
					ProductConstants.productD.equals(product.getId())) {
				return true;
			}
		}
		return false;
	}
	
	@Action
	public void getProductCAndDFirstRule(@Fact("C & D for 45") List<Product> products){
		Double unitPriceProductC = 0D;
		Double unitPriceProductD = 0D;
		Integer productCQuantity = 0;
		Integer productDQuantity = 0;
		Product productC = null;
		Product productD = null;
		for(Product product : products) {
			if(ProductConstants.productC.equals(product.getId())){
				productC = product;
				productCQuantity = product.getQuantity();
				unitPriceProductC = product.getUnitPrice();
			}
			if(ProductConstants.productD.equals(product.getId())){
				productD = product;
				productDQuantity = product.getQuantity();
				unitPriceProductD = product.getUnitPrice();
			}
		}
		
		if(Math.subtractExact(productCQuantity.intValue(),productDQuantity.intValue()) > 0) {
			Double onlyCProductPrice = Math.subtractExact(productCQuantity.intValue(),productDQuantity.intValue()) * unitPriceProductC; 
			Double discountedProductPrice = productDQuantity * 30D;
			productC.setPrice(onlyCProductPrice);
			if(productD != null) {
				productD.setPrice(discountedProductPrice);
			}
			
		}
		if(Math.subtractExact(productCQuantity.intValue(),productDQuantity.intValue()) < 0) {
			Double onlyDProductPrice = Math.subtractExact(productDQuantity.intValue(),productCQuantity.intValue()) * unitPriceProductD; 
			Double discountedProductPrice = productCQuantity * 30D;
			if(productC != null) {
				productC.setPrice(discountedProductPrice);
			}
			
			productD.setPrice(onlyDProductPrice);
		}
		if(Math.subtractExact(productCQuantity.intValue(),productDQuantity.intValue()) == 0) {
			Double discountedProductPrice = productCQuantity * 30D;
			if(productC != null) {
				productC.setPrice(discountedProductPrice);
			}
			if(productD != null) {
				productD.setPrice(0D);
			}
			
		}

	}
}
