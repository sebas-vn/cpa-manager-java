package com.skillstorm.taxr_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxr_manager.models.FilingType;

@Repository
public interface FilingTypeRepository extends CrudRepository<FilingType, Integer>{

}
