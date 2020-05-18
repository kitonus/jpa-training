package com.jatis.training.trainingjpa.dto;

import java.util.List;

import com.jatis.training.trainingjpa.entity.CustomerEntity;

public class CustomerListDto {
	private List<CustomerEntity> list;
	
	public CustomerListDto(List<CustomerEntity> list, long totalCount, int page, int pageSize) {
		this.list = list;
		this.totalCount = totalCount;
		this.page = page;
		this.pageSize = pageSize;
	}
	
	private long totalCount;
	
	private int page;
	
	private int pageSize;

	public List<CustomerEntity> getList() {
		return list;
	}

	public void setList(List<CustomerEntity> list) {
		this.list = list;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
