package com.skillstorm.taxr_manager.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.taxr_manager.dtos.TaxReturnDTO;
import com.skillstorm.taxr_manager.models.TaxReturn;
import com.skillstorm.taxr_manager.repositories.TaxReturnRepository;

@Service
public class TaxReturnService {
	
	private TaxReturnRepository repo;

	public TaxReturnService(TaxReturnRepository repo) {
		this.repo = repo;
	}
	
	public ResponseEntity<Iterable<TaxReturn>> getAll() {
		Iterable<TaxReturn> taxreturns = repo.findAll();
		
		if (taxreturns.iterator().hasNext())
			return ResponseEntity.ok(taxreturns);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Optional<TaxReturn>> getById(int id) {
		Optional<TaxReturn> taxreturn = repo.findById(id);
		
		if (!taxreturn.isEmpty())
			return ResponseEntity.ok(taxreturn);
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<TaxReturn> addTaxReturn(TaxReturnDTO dto) {
		try {
			TaxReturn newTaxReturn = repo.save(new TaxReturn(0, dto.client(), dto.cpa(), dto.filingType(), dto.taxYear(), 
					dto.submissionDate(), null, dto.status(), dto.complexity(), dto.taxAmounts(), dto.categories()));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newTaxReturn);
		} catch (Exception e){
			System.err.println(e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<TaxReturn> updateTaxReturn(int id, TaxReturnDTO dto) {
		try {
			if (repo.existsById(id)) {
				TaxReturn updatedTaxReturn = repo.save(new TaxReturn(0, dto.client(), dto.cpa(), dto.filingType(), dto.taxYear(), 
						dto.submissionDate(), null, dto.status(), dto.complexity(), dto.taxAmounts(), dto.categories()));
				
				return ResponseEntity.ok(updatedTaxReturn); 
			}
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Void> deleteTaxReturn(int id) {
		try {
			repo.deleteById(id); // this will not throw an exception if the record with this id is not in the DB
			return ResponseEntity.noContent().build(); // can't use the .body() method because RE is Void
 		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
