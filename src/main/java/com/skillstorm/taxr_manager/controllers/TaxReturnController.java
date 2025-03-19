package com.skillstorm.taxr_manager.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxr_manager.dtos.TaxReturnDTO;
import com.skillstorm.taxr_manager.models.TaxReturn;
import com.skillstorm.taxr_manager.services.TaxReturnService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/tax-returns")
public class TaxReturnController {

	private TaxReturnService service;
	
	
	public TaxReturnController(TaxReturnService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<TaxReturn>> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<TaxReturn>> getById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@PostMapping()
	public ResponseEntity<TaxReturn> insertCompleteTaxReturn(@RequestBody TaxReturnDTO dto) {
		return service.insertCompleteTaxReturn(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaxReturn> updateCpa(@PathVariable int id, @RequestBody TaxReturnDTO dto) {	
		System.out.println("TAX CONTROLLER " + dto.toString());
		return service.updateTaxReturn(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCpa(@PathVariable int id) {
		return service.deleteTaxReturn(id);
	}

}
