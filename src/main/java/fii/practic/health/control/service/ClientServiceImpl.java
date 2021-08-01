package fii.practic.health.control.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fii.practic.health.entity.model.Client;
import fii.practic.health.entity.repository.Client2Repository;
import fii.practic.health.entity.repository.ClientRepository;

/**
 * 
 * @author bogdan
 * 
 */
@Service
public class ClientServiceImpl implements ClientService {
	private ClientRepository clientRepository;
	private Client2Repository cl2;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository, Client2Repository cl2) {
		super();
		this.clientRepository = clientRepository;
		this.cl2=cl2;
	}

	@Override
	public List<Client> showClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client save(Client client) {

		return clientRepository.save(client);
	}

	@Override
	public Client findClientByUsername(String username) {
		return clientRepository.findClientByUsername(username);
	}


	
	

}
