package com.skillstorm.taxr_manager.dtos;

import java.util.List;

import com.skillstorm.taxr_manager.models.Client;
import com.skillstorm.taxr_manager.models.TaxAmount;

public record TaxReturnDTO(int taxYear, Client client, String filingMethod, String submissionDate, String status, List<TaxAmount> taxAmounts) {
	
}
