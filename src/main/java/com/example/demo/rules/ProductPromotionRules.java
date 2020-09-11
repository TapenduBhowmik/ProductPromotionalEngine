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
public class ProductPromotionRules {
	
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
	public Product getProductAFirstRule(@Fact("3 A's for 130") Product product) {
		
		Double unitPrice = productService.getUnitProductPrice(ProductConstants.productA);
		Double price = 0D;
		Integer offeredQuantity = product.getQuantity()/3;
		Integer nonOfferedQuanity = product.getQuantity() % 3;
		price = offeredQuantity*130D + unitPrice*nonOfferedQuanity;
		product.setPrice(price);
		return product;
	}
	
	@Condition
	public boolean productBFirstRule(@Fact("2 B's for 45") Product product) {
		if(ProductConstants.productB.equals(product.getId())) {
				return true;
		}
		return false;
	}
	
	@Action
	public Product getProductBFirstRule(@Fact("2 B's for 45") Product product) {
		
		Double unitPrice = productService.getUnitProductPrice(ProductConstants.productB);
		Double price = 0D;
		Integer offeredQuantity = product.getQuantity()/2;
		Integer nonOfferedQuanity = product.getQuantity() % 2;
		price = offeredQuantity*45D + unitPrice*nonOfferedQuanity;
		product.setPrice(price);
		return product;
	}
	
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
	public List<Product> getProductCAndDFirstRule(@Fact("C & D for 45") List<Product> products){
		Double unitPriceProductC = productService.getUnitProductPrice(ProductConstants.productC);
		Double unitPriceProductD = productService.getUnitProductPrice(ProductConstants.productD);
		Integer productCQuantity = 0;
		Integer productDQuantity = 0;
		Product productC = null;
		Product productD = null;
		for(Product product : products) {
			if(ProductConstants.productC.equals(product.getId())){
				productC = product;
				productCQuantity = product.getQuantity();
			}
			if(ProductConstants.productD.equals(product.getId())){
				productD = product;
				productDQuantity = product.getQuantity();
			}
		}
		
		if(Math.subtractExact(productCQuantity.intValue(),productDQuantity.intValue()) > 0) {
			Double onlyCProductPrice = Math.subtractExact(productCQuantity.intValue(),productDQuantity.intValue()) * unitPriceProductC; 
			Double discountedProductPrice = productDQuantity * 45D;
			productC.setPrice(onlyCProductPrice);
			productD.setPrice(discountedProductPrice);
		}
		if(Math.subtractExact(productCQuantity.intValue(),productDQuantity.intValue()) < 0) {
			Double onlyDProductPrice = Math.subtractExact(productDQuantity.intValue(),productCQuantity.intValue()) * unitPriceProductD; 
			Double discountedProductPrice = productCQuantity * 45D;
			productC.setPrice(discountedProductPrice);
			productD.setPrice(onlyDProductPrice);
		}
		if(Math.subtractExact(productCQuantity.intValue(),productDQuantity.intValue()) == 0) {
			Double discountedProductPrice = productCQuantity * 45D;
			productC.setPrice(discountedProductPrice);
			productD.setPrice(0D);
		}
		products.add(productC);
		products.add(productD);
		
		return products;
	}
}
