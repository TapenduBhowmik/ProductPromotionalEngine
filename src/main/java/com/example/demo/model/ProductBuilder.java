package com.example.demo.model;

public class ProductBuilder {
	
	private String productId;
	
	private Double price;
	
	private Integer quantity = 0;

	public ProductBuilder() {
		
	}
	public ProductBuilder(String productId, Double price,Integer quantity) {
		super();
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}
	
	public ProductBuilder setProductId(String productId) {
		this.productId = productId;
		return this;
	}
	
	public ProductBuilder setPrice(Double price) {
		this.price = price;
		return this;
	}
	
	public ProductBuilder setQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}
	
	public Product build() {
		return new Product(productId,quantity,price);
	}
	

}
