package com.skillstorm.taxr_manager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;

@Entity
@Table(name = "tax_return")
public class TaxReturn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int taxYear;
	@Column
	private String submissionDate;
	@Column
	private String createdAt;
	
	@ManyToMany
	@JoinTable(name = "return_category", 
	joinColumns = @JoinColumn(name = "tax_return_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;
	
	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;
	@OneToOne
	@JoinColumn(name = "cpa_id", referencedColumnName = "id")
	private Cpa cpa;
	@OneToOne
	@JoinColumn(name = "filing_type_id", referencedColumnName = "id")
	private FilingType filingType;
	@OneToOne
	@JoinColumn(name = "status_id", referencedColumnName = "id")
	private ReturnStatus status;
	@OneToOne
	@JoinColumn(name = "complexity_id", referencedColumnName = "id")
	private ReturnComplexity complexity;
	
	
	@OneToMany(mappedBy = "taxReturn")
	@JsonIgnoreProperties("taxReturn")
	private List<TaxAmount> taxAmounts;

	public TaxReturn() {
		
	}

	public TaxReturn(int id, Client client, Cpa cpa, FilingType filingType, int taxYear, String submissionDate, 
			String createdAt, ReturnStatus status, ReturnComplexity complexity, List<Category> categories) {
		
		super();
		this.id = id;
		this.client = client;
		this.cpa = cpa;
		this.filingType = filingType;
		this.taxYear = taxYear;
		this.submissionDate = submissionDate;
		this.createdAt = createdAt;
		this.status = status;
		this.complexity = complexity;
		this.categories = categories;
		
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

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Cpa getCpa() {
		return cpa;
	}

	public void setCpa(Cpa cpa) {
		this.cpa = cpa;
	}

	public FilingType getFilingType() {
		return filingType;
	}

	public void setFilingType(FilingType filingType) {
		this.filingType = filingType;
	}

	public ReturnStatus getStatus() {
		return status;
	}

	public void setStatus(ReturnStatus status) {
		this.status = status;
	}

	public ReturnComplexity getComplexity() {
		return complexity;
	}

	public void setComplexity(ReturnComplexity complexity) {
		this.complexity = complexity;
	}

	public List<TaxAmount> getTaxAmounts() {
		return taxAmounts;
	}

	public void setTaxAmounts(List<TaxAmount> taxAmounts) {
		this.taxAmounts = taxAmounts;
	}

	@Override
	public String toString() {
		return "TaxReturn [id=" + id + ", taxYear=" + taxYear + ", submissionDate=" + submissionDate + ", createdAt="
				+ createdAt + ", categories=" + categories + ", client=" + client + ", cpa=" + cpa + ", filingType="
				+ filingType + ", status=" + status + ", complexity=" + complexity + ", taxAmounts=" + taxAmounts + "]";
	}
	

}
