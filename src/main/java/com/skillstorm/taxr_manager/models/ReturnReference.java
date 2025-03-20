package com.skillstorm.taxr_manager.models;

public class ReturnReference {
	
	
	private Iterable<State> states;
	private Iterable<FilingType> filingTypes;
	private Iterable<ReturnComplexity> complexities;
	private Iterable<ReturnStatus> statuses; 
	
	public ReturnReference() {
		
	}
	
	public ReturnReference(Iterable<State> states, Iterable<FilingType> filingTypes,
			Iterable<ReturnComplexity> complexities, Iterable<ReturnStatus> statuses) {
		super();
		this.states = states;
		this.filingTypes = filingTypes;
		this.complexities = complexities;
		this.statuses = statuses;
	}

	public Iterable<State> getStates() {
		return states;
	}



	public void setStates(Iterable<State> states) {
		this.states = states;
	}



	public Iterable<FilingType> getFilingTypes() {
		return filingTypes;
	}



	public void setFilingTypes(Iterable<FilingType> filingTypes) {
		this.filingTypes = filingTypes;
	}



	public Iterable<ReturnComplexity> getComplexities() {
		return complexities;
	}



	public void setComplexities(Iterable<ReturnComplexity> complexities) {
		this.complexities = complexities;
	}



	public Iterable<ReturnStatus> getStatuses() {
		return statuses;
	}



	public void setStatuses(Iterable<ReturnStatus> statuses) {
		this.statuses = statuses;
	}


	@Override
	public String toString() {
		return "Reference [states=" + states + ", filingTypes=" + filingTypes + ", complexities=" + complexities
				+ ", statuses=" + statuses + "]";
	}

	

	

}
