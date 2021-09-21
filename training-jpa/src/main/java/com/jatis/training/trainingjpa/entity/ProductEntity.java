package com.jatis.training.trainingjpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mst_product")
public class ProductEntity extends BaseEntity{

	@Id
	@Column(length = 20)
	private String productCode;
	
	@Column(length = 50)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiration;
	
	@ManyToOne
	private BranchEntity creatorBranch;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public BranchEntity getCreatorBranch() {
		return creatorBranch;
	}

	public void setCreatorBranch(BranchEntity creatorBranch) {
		this.creatorBranch = creatorBranch;
	}

}
