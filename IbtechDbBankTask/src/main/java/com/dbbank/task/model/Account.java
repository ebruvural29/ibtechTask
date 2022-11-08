package com.dbbank.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") 
	private long id;
	
	@Column(name="customer_id") 
	private long customerId;
	
	@Column(name="account_name") 
	private String accountName;
	
	@Column(name="balance") 
	private long balance;

	public Account() {
		super();
	}

	public Account(long customerId, String accountName, long balance) {
		super();
		this.customerId = customerId;
		this.accountName = accountName;
		this.balance = balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	
}
