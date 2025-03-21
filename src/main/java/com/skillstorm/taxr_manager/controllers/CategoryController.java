package com.skillstorm.taxr_manager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxr_manager.dtos.CategoryDTO;
import com.skillstorm.taxr_manager.models.Category;
import com.skillstorm.taxr_manager.services.CategoryService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoryService service;
	
	
	public CategoryController(CategoryService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Category>> getAll() {
		return service.getAll();
	}
	
	@PostMapping()
	public ResponseEntity<Category> addCpa(@RequestBody CategoryDTO dto) {
		return service.addCategory(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCpa(@PathVariable int id, @RequestBody CategoryDTO dto) {	
		return service.updateCategory(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCpa(@PathVariable int id) {
		return service.deleteCpa(id);
	}

}
