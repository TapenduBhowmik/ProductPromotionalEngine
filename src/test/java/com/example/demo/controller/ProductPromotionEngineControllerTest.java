package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.abstructTest.AbstractTest;
import com.example.demo.constants.ProductConstants;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.ProductBuilder;

class ProductPromotionEngineControllerTest extends AbstractTest {



	@Test
	public void testScenarioOneWithApi() throws Exception {
		super.setUp();
		String uri = "/getCartTotal";
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
		String inputJson = super.mapToJson(cart);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("Total price of the Cart is : 100.0", content);
	}
	
	@Test
	public void testScenarioTwoWithApi() throws Exception {
		super.setUp();
		String uri = "/getCartTotal";
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
		cart.setProductList(productList);
		String inputJson = super.mapToJson(cart);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("Total price of the Cart is : 370.0", content);
	}
	
	@Test
	public void testScenarioThreeWithApi() throws Exception {
		super.setUp();
		String uri = "/getCartTotal";
		Product productA = new ProductBuilder().setProductId(ProductConstants.productA).setQuantity(3).build();
		Product productB = new ProductBuilder().setProductId(ProductConstants.productB).setQuantity(5).build();
		Product productC = new ProductBuilder().setProductId(ProductConstants.productC).setQuantity(1).build();
		Product productD = new ProductBuilder().setProductId(ProductConstants.productD).setQuantity(1).build();
		List<Product> productList = new ArrayList<Product>();
		productList.add(productA);
		productList.add(productB);
		productList.add(productC);
		productList.add(productD);
		Cart cart = new Cart();
		cart.setOrderId(3L);
		cart.setProductList(productList);
		String inputJson = super.mapToJson(cart);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("Total price of the Cart is : 280.0", content);
	}
}