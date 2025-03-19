package com.skillstorm.taxr_manager.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.taxr_manager.dtos.FilingTypeDTO;
import com.skillstorm.taxr_manager.models.FilingType;
import com.skillstorm.taxr_manager.repositories.FilingTypeRepository;

@Service
public class FilingTypeService {
	
	private FilingTypeRepository repo;

	public FilingTypeService(FilingTypeRepository repo) {
		this.repo = repo;
	}
	
	public ResponseEntity<Iterable<FilingType>> getAll() {
		Iterable<FilingType> types = repo.findAll();
		
		if (types.iterator().hasNext())
			return ResponseEntity.ok(types);
		return ResponseEntity.noContent().build();
	}
	
	
	public ResponseEntity<FilingType> addFilingType(FilingTypeDTO dto) {
		try {
			FilingType newType = repo.save(new FilingType(0, dto.name(), dto.description()));
			return ResponseEntity.status(HttpStatus.CREATED).body(newType);
		} catch (Exception e){
			System.err.println(e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<FilingType> updateClient(int id, FilingTypeDTO dto) {
		try {
			if (repo.existsById(id)) 
				return ResponseEntity.ok(repo.save(new FilingType(0, dto.name(), dto.description())));
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	public ResponseEntity<Void> deleteFilingType(int id) {
		try {
			repo.deleteById(id); // this will not throw an exception if the record with this id is not in the DB
			return ResponseEntity.noContent().build(); // can't use the .body() method because RE is Void
 		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
