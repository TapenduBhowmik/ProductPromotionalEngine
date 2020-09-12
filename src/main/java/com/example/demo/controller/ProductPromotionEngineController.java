package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NoProductInCartException;
import com.example.demo.model.Cart;
import com.example.demo.service.ProductPromotionService;

@RestController
public class ProductPromotionEngineController {
	
	@Value("${product.ids}")
	String productIds;
	
	@Autowired
	ProductPromotionService promotionService;
	
	@PostMapping(value = "/getCartTotal",produces = MediaType.TEXT_PLAIN_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTotalPriceOfCart(@RequestBody Cart cart) throws Exception{
		
		if(cart.getProductList() == null || cart.getProductList().isEmpty()) {
			throw new NoProductInCartException("There is no product in cart");
		}
		
		Double TotalPrice = promotionService.getTotalPriceOfCart(cart.getProductList());
		
		return new ResponseEntity<String>("Total price of the Cart is : " +TotalPrice,HttpStatus.OK);
	}

}
