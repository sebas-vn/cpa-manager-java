package com.skillstorm.taxr_manager.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.taxr_manager.dtos.CpaDTO;
import com.skillstorm.taxr_manager.models.Cpa;
import com.skillstorm.taxr_manager.repositories.CpaRepository;

@Service
public class CpaService {
	
	private CpaRepository repo;

	public CpaService(CpaRepository repo) {
		this.repo = repo;
	}
	
	public ResponseEntity<Iterable<Cpa>> getAll() {
		Iterable<Cpa> cpas = repo.findAll();
		
		if (cpas.iterator().hasNext())
			return ResponseEntity.ok(cpas);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Optional<Cpa>> getById(int id) {
		Optional<Cpa> cpa = repo.findById(id);
		
		if (!cpa.isEmpty())
			return ResponseEntity.ok(cpa);
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Cpa> addCpa(CpaDTO dto) {
		try {
			Cpa newCpa = repo.save(new Cpa(0, dto.firstName(), dto.lastName(), dto.email()));
			return ResponseEntity.status(HttpStatus.CREATED).body(newCpa);
		} catch (Exception e){
			System.err.println(e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Cpa> updateCpa(int id, CpaDTO dto) {
		try {
			if (repo.existsById(id)) 
				return ResponseEntity.ok(repo.save(new Cpa(id, dto.firstName(), dto.lastName(), dto.email())));
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
