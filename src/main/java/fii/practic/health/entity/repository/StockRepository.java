package fii.practic.health.entity.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import fii.practic.health.entity.model.Stock;

public interface StockRepository  extends JpaRepository<Stock, Integer> {

	@Query(value = "SELECT * FROM stock WHERE category = :category_name", nativeQuery = true)
	public List<Stock> printProductsCatName(@Param("category_name") String category_name);
	
	@Query(value = "SELECT DISTINCT category from stock ", nativeQuery = true)
	public <T> List<T> select();
	
	Stock findStockByName(String name);
	
	Stock findStockByCategory(String category);
	
}
