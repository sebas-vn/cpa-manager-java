package com.skillstorm.taxr_manager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.skillstorm.taxr_manager.models.ReturnReference;

import com.skillstorm.taxr_manager.services.ReferenceService;



@RestController
@RequestMapping("/reference")
public class ReferenceController {

	private ReferenceService service;
	
	
	public ReferenceController(ReferenceService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<ReturnReference> getAll() {
		return service.getAll();
	}

}
