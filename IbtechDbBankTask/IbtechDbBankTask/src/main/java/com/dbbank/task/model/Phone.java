package com.dbbank.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="customer_id") 
	private long customerId;
	
	//private Customer customer;
	
	@Column(name="country_code") 
	private String countryCode;
	
	@Column(name="number") 
	private String number;

	public Phone() {
		
	}

	public Phone(long customerId, String countryCode, String number) {
		//this.customer = customer;
		this.customerId = customerId;
		this.countryCode = countryCode;
		this.number = number;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



}
