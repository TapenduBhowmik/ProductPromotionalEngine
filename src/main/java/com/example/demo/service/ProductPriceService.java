package com.example.demo.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.opencsv.CSVReader;

@Service
public class ProductPriceService {
	
	@Value("${product.filename}")
	private String productDetailsFile;
	
	@Autowired
	private ResourceLoader resouceLoader;
	Map<String, Double> productPriceMap = new HashMap<String, Double>();
	@PostConstruct
	public void init(){
		Resource resource = resouceLoader.getResource("classpath:"+ "csv/"+productDetailsFile);
		try {
			Reader reader = Files.newBufferedReader(Paths.get(resource.getURI()));
			CSVReader csvReader = new CSVReader(reader);
		    String[] line;
		    while ((line = csvReader.readNext()) != null) {
		    	productPriceMap.put(line[0], Double.parseDouble(line[1]));
		    }
		    reader.close();
		    csvReader.close();
		} catch (IOException e) {
			
		}
	}
	
	public Double getUnitProductPrice(String productId) {
		Double unitPrice = productPriceMap.get(productId);
		return unitPrice;
	}

}
