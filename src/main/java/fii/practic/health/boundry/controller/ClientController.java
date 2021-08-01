package fii.practic.health.boundry.controller;

import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fii.practic.health.boundry.dto.ErrorDTO;
import fii.practic.health.control.service.ClientService;
import fii.practic.health.entity.model.Client;

@RestController
@RequestMapping(value = "/api/appointments")
@CrossOrigin
public class ClientController {

	private ClientService client;

	@Autowired
	public ClientController(ClientService client) {
		super();
		this.client = client;
	}

	@GetMapping
	public ResponseEntity<?> getAppointments() {
		List<Client> appointments = client.showClients();
		return ResponseEntity.ok(appointments);
	}

	@PostMapping
	public Object saveClient(@RequestParam String username, @RequestParam int balance)
			throws PSQLException, NumberFormatException {

		if (username.trim().equalsIgnoreCase(""))
			return new ResponseEntity<>(new ErrorDTO(400, "Completati username!"), HttpStatus.BAD_REQUEST);

		Client c = new Client();
		c.setBalance(balance);
		c.setUsername(username);

		try {
			return new ResponseEntity<>(client.save(c), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException ee) {
			return ee.getRootCause().getLocalizedMessage();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CREATED);
		}

	}

	@PostMapping(value = "/findClient")
	public ResponseEntity<?> getClient(@RequestParam String username) {
		return ResponseEntity.ok(client.findClientByUsername(username));
	}


}
