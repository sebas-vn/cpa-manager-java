package com.skillstorm.taxr_manager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;

@Entity
@Table(name = "return")
public class TaxReturn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int taxYear;
	@Column
	private Client client;
	@Column
	private String filingMethod;
	@Column
	private String submissionDate;
	@Column
	private String status;
	
	@OneToMany
	private List<TaxAmount> taxAmounts;

	public TaxReturn() {
		
	}

	public TaxReturn(int id, int taxYear, Client client, String filingMethod, String submissionDate, String status, List<TaxAmount> taxAmounts) {
		super();
		this.id = id;
		this.taxYear = taxYear;
		this.client = client;
		this.filingMethod = filingMethod;
		this.submissionDate = submissionDate;
		this.status = status;
		this.taxAmounts = taxAmounts;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getFilingMethod() {
		return filingMethod;
	}

	public void setFilingMethod(String filingMethod) {
		this.filingMethod = filingMethod;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TaxAmount> getTaxAmounts() {
		return taxAmounts;
	}

	public void setTaxAmounts(List<TaxAmount> taxAmounts) {
		this.taxAmounts = taxAmounts;
	}

	@Override
	public String toString() {
		return "TaxReturn [id=" + id + ", taxYear=" + taxYear + ", client=" + client + ", filingMethod=" + filingMethod
				+ ", submissionDate=" + submissionDate + ", status=" + status + ", taxAmounts=" + taxAmounts + "]";
	}

}
