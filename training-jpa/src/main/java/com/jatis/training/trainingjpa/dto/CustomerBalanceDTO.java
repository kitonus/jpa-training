package com.jatis.training.trainingjpa.dto;

import java.math.BigDecimal;

import com.jatis.training.trainingjpa.entity.CustomerEntity;

public class CustomerBalanceDTO {
	
	private CustomerEntity customer;
	private BigDecimal balance;
	
	public CustomerBalanceDTO(CustomerEntity customer, BigDecimal balance) {
		super();
		this.customer = customer;
		this.balance = balance;
	}
	
	public CustomerEntity getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
