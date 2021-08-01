package fii.practic.health.boundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fii.practic.health.control.service.ClientService;
import fii.practic.health.control.service.PurchaseService;
import fii.practic.health.control.service.StockService;
import fii.practic.health.entity.model.Client;
import fii.practic.health.entity.model.Stock;

@RestController
@RequestMapping(value = "/api/purchase")
@CrossOrigin
public class PurchaseController {

	private static ClientService client;
	private static StockService stockService;
	private static PurchaseService purchase;

	@Autowired
	public PurchaseController(ClientService client, StockService stockService, PurchaseService purchase) {
		super();
		this.client = client;
		this.stockService = stockService;
		this.purchase = purchase;
	}

	@PatchMapping(value = "/printProductsCategoryName")
	public static ResponseEntity<?> BuyForUser(@RequestParam String commandParam) throws Exception {

		String[] command = commandParam.split("\\s");
		if (command.length != 5)
			return new ResponseEntity<>("Comanda introdusa gresit!", HttpStatus.BAD_REQUEST);
		String whatToDo = command[0];
		String name = command[1];
		int quantity = Integer.parseInt(command[2]);
		String username = command[4];
		
		Client cl = client.findClientByUsername(username);
		Stock st= stockService.findStockByName(name);

		int productPrice = st.getPrice();
		int clientBalance = cl.getBalance();
		int maxQuantity= st.getMaxQuantity();

		if (whatToDo.equalsIgnoreCase("buy")) {
			if (quantity > maxQuantity )
				return new ResponseEntity<>("Nu exista suficient stock. A mai ramas: " + maxQuantity + " " +
			name, HttpStatus.BAD_REQUEST);
			else
				if (clientBalance > productPrice * quantity) {
				    st.setQuantity(st.getQuantity()- quantity);
					cl.setBalance(cl.getBalance()-(productPrice * quantity));
					purchase.buy(st,cl);
					System.out.println("User "+ username + " has bought " + quantity + " " + name );
					
				}
		} else {
			return new ResponseEntity<>("Comanda " + whatToDo + " nu exista!", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("User: " + username + " has bought " + quantity + " " + name, HttpStatus.ACCEPTED);
	}

}
