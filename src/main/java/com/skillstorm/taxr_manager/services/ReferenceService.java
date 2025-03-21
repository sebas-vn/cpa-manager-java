package com.skillstorm.taxr_manager.services;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.skillstorm.taxr_manager.models.FilingType;
import com.skillstorm.taxr_manager.models.ReturnComplexity;
import com.skillstorm.taxr_manager.models.ReturnReference;
import com.skillstorm.taxr_manager.models.ReturnStatus;
import com.skillstorm.taxr_manager.models.State;
import com.skillstorm.taxr_manager.repositories.FilingTypeRepository;
import com.skillstorm.taxr_manager.repositories.ReturnComplexityRepository;
import com.skillstorm.taxr_manager.repositories.ReturnStatusRepository;
import com.skillstorm.taxr_manager.repositories.StateRepository;

@Service
public class ReferenceService {
	
	private StateRepository stateRepo;
	private FilingTypeRepository filingRepo;
	private ReturnComplexityRepository complexityRepo;
	private ReturnStatusRepository statusRepo;

	
	
	public ReferenceService(StateRepository stateRepo, FilingTypeRepository filingRepo,
			ReturnComplexityRepository complexityRepo, ReturnStatusRepository statusRepo) {
		this.stateRepo = stateRepo;
		this.filingRepo = filingRepo;
		this.complexityRepo = complexityRepo;
		this.statusRepo = statusRepo;
	}

	public ResponseEntity<ReturnReference> getAll() {
		
		Iterable<State> state = stateRepo.findAll();
		Iterable<FilingType> filingType = filingRepo.findAll();
		Iterable<ReturnComplexity> complexities = complexityRepo.findAll();
		Iterable<ReturnStatus> statuses = statusRepo.findAll();
		
		ReturnReference reference = new ReturnReference(state, filingType, complexities, statuses);
		
		return ResponseEntity.ok(reference);
	}

}
