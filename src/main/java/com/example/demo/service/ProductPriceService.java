package com.example.demo.service;

import java.io.BufferedReader;
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
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

@Service
public class ProductPriceService {

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
		    }
		    reader.close();
		    csvReader.close();
		    
		} catch (Exception e) {
			System.out.println(e);
		}
		return productPriceMap;
	}
	

}
