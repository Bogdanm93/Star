package fii.practic.health.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fii.practic.health.entity.model.Client;
import fii.practic.health.entity.model.Stock;
import fii.practic.health.entity.repository.Client2Repository;
import fii.practic.health.entity.repository.ClientRepository;
import fii.practic.health.entity.repository.Stock2Repository;
import fii.practic.health.entity.repository.StockRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private StockRepository stock;
	private ClientRepository client;
	private Client2Repository client2;
	private Stock2Repository stock2;
	
	@Autowired
	public PurchaseServiceImpl(StockRepository stock, ClientRepository client, Client2Repository client2,Stock2Repository stock2) {
		super();
		this.stock = stock;
		this.client = client;
		this.client2=client2;
		this.stock2=stock2;
	}



	@Override
	public void buy(Stock stockParam, Client clientParam) {
		client2.modifyClient(clientParam);
		stock2.modifiStock(stockParam);
	}



	

}
