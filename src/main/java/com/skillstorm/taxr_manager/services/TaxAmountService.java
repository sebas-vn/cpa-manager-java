package com.skillstorm.taxr_manager.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.taxr_manager.dtos.ClientDTO;
import com.skillstorm.taxr_manager.dtos.TaxAmountDTO;
import com.skillstorm.taxr_manager.models.Client;
import com.skillstorm.taxr_manager.models.TaxAmount;
import com.skillstorm.taxr_manager.repositories.ClientRepository;
import com.skillstorm.taxr_manager.repositories.TaxAmountRepository;

@Service
public class TaxAmountService {
	
	private TaxAmountRepository repo;

	public TaxAmountService(TaxAmountRepository repo) {
		this.repo = repo;
	}
	
	public ResponseEntity<Optional<TaxAmount>> getById(int id) {
		Optional<TaxAmount> taxAmounts = repo.findById(id);
		
		if (!taxAmounts.isEmpty())
			return ResponseEntity.ok(taxAmounts);
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<TaxAmount> addTaxAmount(TaxAmountDTO dto) {
		try {
			TaxAmount newTaxAmount = repo.save(new TaxAmount(0, dto.adjustedGrossIncome(), dto.taxableIncome(), 
					dto.taxLiability(), dto.refundAmount(), dto.state(), dto.taxReturn()));
			return ResponseEntity.status(HttpStatus.CREATED).body(newTaxAmount);
		} catch (Exception e){
			System.err.println(e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<TaxAmount> updateTaxAmount(int id, TaxAmountDTO dto) {
		try {
			if (repo.existsById(id)) 
				return ResponseEntity.ok(repo.save(new TaxAmount(0, dto.adjustedGrossIncome(), dto.taxableIncome(), 
						dto.taxLiability(), dto.refundAmount(), dto.state(), dto.taxReturn())));
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Void> deleteTaxAmount(int id) {
		try {
			repo.deleteById(id); // this will not throw an exception if the record with this id is not in the DB
			return ResponseEntity.noContent().build(); // can't use the .body() method because RE is Void
 		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
