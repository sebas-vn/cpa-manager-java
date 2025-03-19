package com.skillstorm.taxr_manager.dtos;

import java.util.Optional;

import com.skillstorm.taxr_manager.models.State;
import com.skillstorm.taxr_manager.models.TaxReturn;

public record TaxAmountDTO(int adjustedGrossIncome, int taxableIncome, int taxLiability, int refundAmount, State state, TaxReturn taxReturn) {
	
}
