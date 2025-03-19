package com.skillstorm.taxr_manager.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.taxr_manager.dtos.ClientDTO;
import com.skillstorm.taxr_manager.models.Client;
import com.skillstorm.taxr_manager.repositories.ClientRepository;

@Service
public class ClientService {
	
	private ClientRepository repo;

	public ClientService(ClientRepository repo) {
		this.repo = repo;
	}
	
	public ResponseEntity<Iterable<Client>> getAll() {
		Iterable<Client> clients = repo.findAll();
		
		if (clients.iterator().hasNext())
			return ResponseEntity.ok(clients);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Optional<Client>> getById(int id) {
		Optional<Client> client = repo.findById(id);
		
		if (!client.isEmpty())
			return ResponseEntity.ok(client);
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Client> addClient(ClientDTO dto) {
		try {
			Client newClient = repo.save(new Client(0, dto.firstName(), dto.middleName(), dto.lastName(), dto.email(),
					dto.mobileNumber(), dto.homeNumber(), dto.ssn(), dto.ein()));
			return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
		} catch (Exception e){
			System.err.println(e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Client> updateClient(int id, ClientDTO dto) {
		try {
			if (repo.existsById(id)) 
				return ResponseEntity.ok(repo.save(new Client(0, dto.firstName(), dto.middleName(), dto.lastName(), dto.email(),
						dto.mobileNumber(), dto.homeNumber(), dto.ssn(), dto.ein())));
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Void> deleteClient(int id) {
		try {
			repo.deleteById(id); // this will not throw an exception if the record with this id is not in the DB
			return ResponseEntity.noContent().build(); // can't use the .body() method because RE is Void
 		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
