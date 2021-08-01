package fii.practic.health.entity.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fii.practic.health.entity.model.Client;


@Repository
public class Client2Repository {
	@Autowired
	private JdbcTemplate jdbcTemplate;


	public void modifyClient(Client client) {
		jdbcTemplate.update("update client set balance = ? where id = ?",
				new Object[] { client.getBalance(), client.getId() });
	}
}
