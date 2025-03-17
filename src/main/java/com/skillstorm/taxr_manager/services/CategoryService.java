package com.skillstorm.taxr_manager.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.taxr_manager.dtos.CategoryDTO;
import com.skillstorm.taxr_manager.dtos.CpaDTO;
import com.skillstorm.taxr_manager.models.Category;
import com.skillstorm.taxr_manager.models.Cpa;
import com.skillstorm.taxr_manager.repositories.CategoryRepository;
import com.skillstorm.taxr_manager.repositories.CpaRepository;

@Service
public class CategoryService {
	
	private CategoryRepository repo;

	public CategoryService(CategoryRepository repo) {
		this.repo = repo;
	}
	
	public ResponseEntity<Iterable<Category>> getAll() {
		Iterable<Category> categories = repo.findAll();
		
		if (categories.iterator().hasNext())
			return ResponseEntity.ok(categories);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Optional<Category>> getById(int id) {
		Optional<Category> category = repo.findById(id);
		
		if (!category.isEmpty())
			return ResponseEntity.ok(category);
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Category> addCategory(CategoryDTO dto) {
		try {
			Category newCategory = repo.save(new Category(0, dto.name(), dto.description()));
			return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
		} catch (Exception e){
			System.err.println(e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Category> updateCategory(int id, CategoryDTO dto) {
		try {
			if (repo.existsById(id)) 
				return ResponseEntity.ok(repo.save(new Category(id, dto.name(), dto.description())));
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Void> deleteCpa(int id) {
		try {
			repo.deleteById(id); // this will not throw an exception if the record with this id is not in the DB
			return ResponseEntity.noContent().build(); // can't use the .body() method because RE is Void
 		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
