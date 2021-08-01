package fii.practic.health.entity.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fii.practic.health.entity.model.Stock;

@Repository
public class Stock2Repository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Stock> selectStockByCatName(String category) {
		String sql= "Select * from stock where LOWER(category) LIKE LOWER('"+ category + "')";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Stock.class));
	}
	
	public List<Stock> selectStockByName(String name){
		String sql= "Select * from stock where LOWER(name) LIKE LOWER('"+ name + "')";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Stock.class));
	}

	public void modifiStock(Stock stockParam) {
		jdbcTemplate.update("update stock set quantity = ? where id = ?",
				new Object[] { stockParam.getQuantity(), stockParam.getId() });
		
	}
}
