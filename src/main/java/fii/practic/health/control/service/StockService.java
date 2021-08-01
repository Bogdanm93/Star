package fii.practic.health.control.service;

import java.util.List;

import fii.practic.health.entity.model.Client;
import fii.practic.health.entity.model.Stock;

public interface StockService {

	
	List<Stock> showStock();
	
	Stock save (Stock stock);
	
	List<Stock> printProductsCategory(String catName);
	
	 <T> List<T> select();

	Stock findStockByName(String name);
	
	List<Stock> getProductsByName(String name);
	 
}
