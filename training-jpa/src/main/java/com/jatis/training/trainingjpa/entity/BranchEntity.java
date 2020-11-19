package com.jatis.training.trainingjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TRA_MST_BRANCH")
public class BranchEntity extends BaseEntity{

	@Id
	@Column(length = 10)
	@Size(min = 3, max = 10)
	private String branchId;
	
	@Column(length = 100)
	@NotEmpty(message = "name cannot be ampty Bro!")
	private String name;
	
	@Column(length = 300)
	@Size(max = 300)
	private String address;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
