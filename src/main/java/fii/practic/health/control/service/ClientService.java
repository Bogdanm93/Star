package fii.practic.health.control.service;


import java.util.List;

import fii.practic.health.entity.model.Client;


public interface ClientService {

	
	List<Client> showClients();
	
	Client save (Client client);
	
	Client findClientByUsername(String username);

}
