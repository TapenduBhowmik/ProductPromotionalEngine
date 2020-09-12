package com.example.demo.service;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

@Service
public class ProductPriceService {

	Logger logger = LoggerFactory.getLogger(ProductPriceService.class);
	@Cacheable("map")
	@PostConstruct
	public Map<String, Double> init(){
		Map<String, Double> productPriceMap = new HashMap<String, Double>();
		
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get(
				      ClassLoader.getSystemResource("csv/" + "productprice.csv").toURI()));
			CSVReader csvReader = new CSVReader(reader);
		    String[] line;
		    while ((line = csvReader.readNext()) != null) {
		    	productPriceMap.put(line[0], Double.parseDouble(line[1]));
		    	logger.info("Product Id is " + line[0] + " and Product unit price is " + Double.parseDouble(line[1]));
		    }
		    reader.close();
		    csvReader.close();
		    
		} catch (Exception e) {
			System.out.println(e);
		}
		return productPriceMap;
	}
	

}
