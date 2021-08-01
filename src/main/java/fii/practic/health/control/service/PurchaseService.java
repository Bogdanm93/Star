package fii.practic.health.control.service;

import fii.practic.health.entity.model.Client;
import fii.practic.health.entity.model.Stock;

public interface PurchaseService {

	
	
	void buy(Stock s, Client c);
}
