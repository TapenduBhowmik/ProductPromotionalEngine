package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;

@Service
public class ProductPriceService {
	
	@Value("${product.filename}")
	private String productDetailsFile;
	
	@Autowired
	private ResourceLoader resouceLoader;
	
	@PostConstruct
	public List<Product> getProductAndPriceDetails(){
		Resource resource = resouceLoader.getResource("classpath:"+ "csv/"+productDetailsFile);
		return new ArrayList<Product>();
	}
	
	public Double getUnitProductPrice(String productId) {
		Double unitPrice = null;
		for(Product unitProduct : this.getProductAndPriceDetails()){
			if(productId.equals(unitProduct.getId())) {
				unitPrice = unitProduct.getPrice();
			}
		}
		return unitPrice;
	}

}
