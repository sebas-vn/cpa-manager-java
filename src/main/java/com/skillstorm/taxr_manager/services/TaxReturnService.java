package com.skillstorm.taxr_manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.taxr_manager.dtos.TaxReturnDTO;
import com.skillstorm.taxr_manager.models.TaxAmount;
import com.skillstorm.taxr_manager.models.TaxReturn;
import com.skillstorm.taxr_manager.repositories.TaxAmountRepository;
import com.skillstorm.taxr_manager.repositories.TaxReturnRepository;

import jakarta.transaction.Transactional;

@Service
public class TaxReturnService {
	
	private TaxReturnRepository repo;
	private TaxAmountRepository taxAmountRepo;

	public TaxReturnService(TaxReturnRepository repo, TaxAmountService taxAmountService, TaxAmountRepository taxAmountrepo) {
		this.repo = repo;
		this.taxAmountRepo = taxAmountrepo;
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
	
	// TBD
	public ResponseEntity<TaxReturn> addTaxReturn(TaxReturnDTO dto) {
		try {
			TaxReturn newTaxReturn = repo.save(new TaxReturn(0, dto.client(), dto.cpa(), dto.filingType(), dto.taxYear(), 
					dto.submissionDate(), null, dto.status(), dto.complexity(), dto.categories()));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newTaxReturn);
		} catch (Exception e){
			System.err.println(e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	// insertCompleteTaxReturn inserts the record of a TaxReturn 
	// also inserts records of an joint tables it might be related to
	@Transactional
	public ResponseEntity<TaxReturn> insertCompleteTaxReturn(TaxReturnDTO dto) {
		try {
			TaxReturn newTaxReturn = new TaxReturn(0, dto.client(), dto.cpa(), dto.filingType(),
					dto.taxYear(), dto.submissionDate(), null, dto.status(), dto.complexity(), dto.categories());
			
			// Reurns TaxReturn with its proper ID
			TaxReturn savedTaxReturn = repo.save(newTaxReturn);
			
			if (dto.taxAmounts().size() > 0) {
				dto.taxAmounts().forEach(amount -> amount.setId(0));
				
				// convert new DTO records to instances of the Class TaxAmount
				Iterable<TaxAmount> taxAmounts = taxAmountRepo.saveAll(dto.taxAmounts());
				List<TaxAmount> tAmount = new ArrayList<>();
				
				for (TaxAmount ta : taxAmounts) {
					tAmount.add(ta);
				}
				
				// Insert list of recently inserted TaxAmounts to new ReturnTax
				savedTaxReturn.setTaxAmounts(tAmount);
				
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(savedTaxReturn);
			
		} catch (Exception e) {
			System.err.println(e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<TaxReturn> updateTaxReturn(int id, TaxReturnDTO dto) {
		try {
			
			System.out.println("ID TAX:  " + id);
			
			if (repo.existsById(id)) {

				TaxReturn newTaxReturn = new TaxReturn(id, dto.client(), dto.cpa(), dto.filingType(),
						dto.taxYear(), dto.submissionDate(), null, dto.status(), dto.complexity(), dto.categories());
				
				System.out.println(newTaxReturn.toString());
				
				// Reurns TaxReturn with its proper ID
				TaxReturn savedTaxReturn = repo.save(newTaxReturn);
				
				dto.taxAmounts().forEach(amount -> amount.setTaxReturn(savedTaxReturn));
								
				// Update taxAmounts in tax_amounts / for updates there are already IDs in place
				Iterable<TaxAmount> taxAmounts = taxAmountRepo.saveAll(dto.taxAmounts());
				List<TaxAmount> tAmount = new ArrayList<>();
				
				for (TaxAmount ta : taxAmounts) {
					tAmount.add(ta);
				}
				
				// Insert list of recently inserted TaxAmounts to new ReturnTax
				savedTaxReturn.setTaxAmounts(tAmount);
				
				
				return ResponseEntity.ok(savedTaxReturn);
				
			}
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} catch (Exception e) {
			System.err.println(e);
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
