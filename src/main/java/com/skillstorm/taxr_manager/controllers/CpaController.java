package com.skillstorm.taxr_manager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxr_manager.dtos.CpaDTO;
import com.skillstorm.taxr_manager.models.Cpa;
import com.skillstorm.taxr_manager.services.CpaService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/cpa")
public class CpaController {

	private CpaService service;
	
	
	public CpaController(CpaService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Cpa>> getAll() {
		return service.getAll();
	}
	
	@PostMapping()
	public ResponseEntity<Cpa> addCpa(@RequestBody CpaDTO dto) {
		return service.addCpa(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cpa> updateCpa(@PathVariable int id, @RequestBody CpaDTO dto) {	
		return service.updateCpa(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCpa(@PathVariable int id) {
		return service.deleteCpa(id);
	}

}
