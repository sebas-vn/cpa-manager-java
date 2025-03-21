package com.skillstorm.taxr_manager.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxr_manager.dtos.TaxAmountDTO;
import com.skillstorm.taxr_manager.models.TaxAmount;
import com.skillstorm.taxr_manager.services.TaxAmountService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/tax-amounts")
public class TaxAmountController {

	private TaxAmountService service;
	
	
	public TaxAmountController(TaxAmountService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<TaxAmount>> getById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@PostMapping()
	public ResponseEntity<TaxAmount> addTaxAmount(@RequestBody TaxAmountDTO dto) {
		return service.addTaxAmount(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaxAmount> updateTaxAmount(@PathVariable int id, @RequestBody TaxAmountDTO dto) {	
		return service.updateTaxAmount(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTaxAmount(@PathVariable int id) {
		return service.deleteTaxAmount(id);
	}

}
