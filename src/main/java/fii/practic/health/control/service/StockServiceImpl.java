package fii.practic.health.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fii.practic.health.entity.model.Stock;
import fii.practic.health.entity.repository.Stock2Repository;
import fii.practic.health.entity.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService{

	private StockRepository stock;
	private Stock2Repository stock2;
	@Autowired
	public StockServiceImpl(StockRepository stock, Stock2Repository stock2) {
		super();
		this.stock = stock;
		this.stock2=stock2;
	}
	
	@Override
	public List<Stock> showStock() {
		return stock.findAll();
	}

	@Override
	public Stock save(Stock stockParam) {
		return stock.save(stockParam);
	}

	@Override
	public List<Stock> printProductsCategory(String name ) {
		return stock2.selectStockByCatName(name);
		
	}

	@Override
	public List<Object> select() {
		return stock.select();
	}

	@Override
	public Stock findStockByName(String name) {
		return stock.findStockByName(name);
	}

	@Override
	public List<Stock> getProductsByName(String name) {
		return stock2.selectStockByName(name);
	}

	
}
