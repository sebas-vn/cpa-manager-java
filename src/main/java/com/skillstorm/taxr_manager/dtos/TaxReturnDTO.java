package com.skillstorm.taxr_manager.dtos;

import java.util.List;

import com.skillstorm.taxr_manager.models.Category;
import com.skillstorm.taxr_manager.models.Client;
import com.skillstorm.taxr_manager.models.Cpa;
import com.skillstorm.taxr_manager.models.FilingType;
import com.skillstorm.taxr_manager.models.ReturnComplexity;
import com.skillstorm.taxr_manager.models.ReturnStatus;
import com.skillstorm.taxr_manager.models.TaxAmount;

public record TaxReturnDTO(Client client, Cpa cpa, FilingType filingType, int taxYear, String submissionDate, 
		ReturnStatus status, ReturnComplexity complexity, List<TaxAmount> taxAmounts, List<Category> categories) {
	
}
