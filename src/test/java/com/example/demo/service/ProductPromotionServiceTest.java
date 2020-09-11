package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.demo.constants.ProductConstants;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.ProductBuilder;

class ProductPromotionServiceTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void testScenarioOne() {
		Product productA = new ProductBuilder().setProductId(ProductConstants.productA).setQuantity(1).build();
		Product productB = new ProductBuilder().setProductId(ProductConstants.productB).setQuantity(1).build();
		Product productC = new ProductBuilder().setProductId(ProductConstants.productC).setQuantity(1).build();
		List<Product> productList = new ArrayList<Product>();
		productList.add(productA);
		productList.add(productB);
		productList.add(productC);
		Cart cart = new Cart();
		cart.setOrderId(1L);
		cart.setProductList(productList);
		fail("Not yet implemented");
	}
	
	@Test
	void testScenarioTwo() {
		Product productA = new ProductBuilder().setProductId(ProductConstants.productA).setQuantity(5).build();
		Product productB = new ProductBuilder().setProductId(ProductConstants.productB).setQuantity(5).build();
		Product productC = new ProductBuilder().setProductId(ProductConstants.productC).setQuantity(1).build();
		List<Product> productList = new ArrayList<Product>();
		productList.add(productA);
		productList.add(productB);
		productList.add(productC);
		Cart cart = new Cart();
		cart.setOrderId(2L);
		cart.setProductList(productList);
		fail("Not yet implemented");
	}
	
	@Test
	void testScenarioThree() {
		Product productA = new ProductBuilder().setProductId(ProductConstants.productA).setQuantity(5).build();
		Product productB = new ProductBuilder().setProductId(ProductConstants.productB).setQuantity(5).build();
		Product productC = new ProductBuilder().setProductId(ProductConstants.productC).setQuantity(3).build();
		Product productD = new ProductBuilder().setProductId(ProductConstants.productD).setQuantity(1).build();
		List<Product> productList = new ArrayList<Product>();
		productList.add(productA);
		productList.add(productB);
		productList.add(productC);
		productList.add(productD);
		Cart cart = new Cart();
		cart.setOrderId(3L);
		cart.setProductList(productList);
		fail("Not yet implemented");
	}

}
