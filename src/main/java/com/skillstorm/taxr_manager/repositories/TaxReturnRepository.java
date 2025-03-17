package com.skillstorm.taxr_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxr_manager.models.TaxReturn;


@Repository
public interface TaxReturnRepository extends CrudRepository<TaxReturn, Integer>{

}
