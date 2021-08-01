package fii.practic.health.boundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fii.practic.health.control.service.StockService;
import fii.practic.health.entity.model.Stock;

@RestController
@RequestMapping(value = "/api/Stock")
@CrossOrigin
public class StockController {
	

	private static StockService stockService;
	
	@Autowired
	public StockController(StockService stock) {
		super();
		this.stockService = stock;
	}
	
	
	@GetMapping
	public ResponseEntity<?> getAppointments() {
		List<Stock> stockList = stockService.showStock();
		return ResponseEntity.ok(stockList);
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestParam String category, @RequestParam String name, @RequestParam int quantity,
			@RequestParam int price, @RequestParam int maxQuantity) {
		Stock stock= new Stock();
		stock.setCategory(category);
		stock.setMaxQuantity(maxQuantity);
		stock.setName(name);
		stock.setPrice(price);
		stock.setQuantity(quantity);
		
		return new ResponseEntity<>(stockService.save(stock), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/printProductsCategoryName")
	public static List<Stock> getProductsCatName(@RequestParam String catName) {
		List<Stock> stockList =stockService.printProductsCategory(catName);
		for(Stock s : stockList) {
			System.out.println(s.getId() + " " + s.getName() + " " + s.getQuantity() + " " + s.getPrice() );
		}
		return stockList;
		
	}
	
	@GetMapping(value = "/printProductsAll")
	public static String printProductsAll() {
		List<Stock> stockList =stockService.showStock();
		String result="";
		for(Stock s : stockList) {
			result = result + s.getId() + " " + s.getName() + " " + s.getQuantity() + " " + s.getCategory() + " " +
		s.getPrice() + "\n";
			System.out.println(result);
		}
		result= result.substring(0, result.length() - 1);
		return result;
		
	}
	
	
	@GetMapping(value = "/printProductsName")
	public static String printProductsName(@RequestParam String name) {
		List<Stock> stockList =stockService.getProductsByName(name);
		String result="";
		for(Stock s : stockList) {
			result = result + " " + s.getName() + " " + s.getQuantity() + " "  + s.getPrice() + "\n";
		}
		System.out.println(result);
		return result;
		
	}
	
	
	@GetMapping(value = "/printCategories")
	public static String printCategories() {
		System.out.println(stockService.select().toString());
		return stockService.select().toString();
		
	}

}
