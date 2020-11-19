package com.jatis.training.trainingjpa.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mst_account")
public class AccountEntity extends BaseEntity{

	@Id
	@NotNull
	@Size(max = 8, min = 8)
	@Column(length = 20)
	private String accountNo;

	@NotNull
	@ManyToOne(optional = false)
	private CustomerEntity customer;

	@Max(value = 100000000l)
	private BigDecimal balance;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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
