package fii.practic.health.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "category", updatable = false, nullable = false)
	private String category;

	@Column(name = "name", updatable = false, nullable = false)
	private String name;

	@Column(name = "quantity", updatable = false, nullable = false)
	private int quantity;

	@Column(name = "price", updatable = false, nullable = false)
	private int price;

	@Column(name = "maxQuantity", updatable = false, nullable = false)
	private int maxQuantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public Stock(int id, String category, String name, int quantity, int price, int maxQuantity) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.maxQuantity = maxQuantity;
	}

	public Stock() {

	}

}
