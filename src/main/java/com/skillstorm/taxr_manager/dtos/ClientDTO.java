package com.skillstorm.taxr_manager.dtos;


public record ClientDTO(String firstName, String middleName, String lastName, 
		String email, String phoneNumber, String ssn, String ein) {
	
}
