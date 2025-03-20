package com.skillstorm.taxr_manager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxr_manager.dtos.ClientDTO;
import com.skillstorm.taxr_manager.dtos.CpaDTO;
import com.skillstorm.taxr_manager.models.Client;
import com.skillstorm.taxr_manager.models.Cpa;
import com.skillstorm.taxr_manager.services.ClientService;
import com.skillstorm.taxr_manager.services.CpaService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/client")
public class ClientController {

	private ClientService service;
	
	
	public ClientController(ClientService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Client>> getAll() {
		return service.getAll();
	}
	
	@PostMapping()
	public ResponseEntity<Client> addCpa(@RequestBody ClientDTO dto) {
		return service.addClient(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateCpa(@PathVariable int id, @RequestBody ClientDTO dto) {	
		return service.updateClient(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCpa(@PathVariable int id) {
		return service.deleteClient(id);
	}

}
