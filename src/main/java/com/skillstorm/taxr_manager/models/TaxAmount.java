package com.skillstorm.taxr_manager.models;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tax_amounts")
public class TaxAmount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int adjustedGrossIncome;
	@Column
	private int taxableIncome;
	@Column
	private int taxLiability;
	@Column
	private int refundAmount;
	
	@OneToOne
	@JoinColumn(name = "id_state", referencedColumnName = "id")
	private State state;
	
	@ManyToOne
	@JoinColumn(name = "id_return", referencedColumnName = "id")
	@JsonIgnoreProperties("taxAmounts")
	private TaxReturn taxReturn;
	
	public TaxAmount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaxAmount(int id, int adjustedGrossIncome, int taxableIncome, int taxLiability, int refundAmount,
			State state, TaxReturn taxReturn) {
		super();
		this.id = id;
		this.adjustedGrossIncome = adjustedGrossIncome;
		this.taxableIncome = taxableIncome;
		this.taxLiability = taxLiability;
		this.refundAmount = refundAmount;
		this.state = state;
		this.taxReturn = taxReturn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdjustedGrossIncome() {
		return adjustedGrossIncome;
	}

	public void setAdjustedGrossIncome(int adjustedGrossIncome) {
		this.adjustedGrossIncome = adjustedGrossIncome;
	}

	public int getTaxableIncome() {
		return taxableIncome;
	}

	public void setTaxableIncome(int taxableIncome) {
		this.taxableIncome = taxableIncome;
	}

	public int getTaxLiability() {
		return taxLiability;
	}

	public void setTaxLiability(int taxLiability) {
		this.taxLiability = taxLiability;
	}

	public int getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public TaxReturn getTaxReturn() {
		return taxReturn;
	}

	public void setTaxReturn(TaxReturn taxReturn) {
		this.taxReturn = taxReturn;
	}

	@Override
	public String toString() {
		return "TaxAmount [id=" + id + ", adjustedGrossIncome=" + adjustedGrossIncome + ", taxableIncome="
				+ taxableIncome + ", taxLiability=" + taxLiability + ", refundAmount=" + refundAmount + ", state="
				+ state + ", taxReturn=" + taxReturn + "]";
	}

}
