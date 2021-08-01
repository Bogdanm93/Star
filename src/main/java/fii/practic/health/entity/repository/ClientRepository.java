package fii.practic.health.entity.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fii.practic.health.entity.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	
	Client findClientByUsername(String username);
	
	Client findCLientById(int id);
	
}
