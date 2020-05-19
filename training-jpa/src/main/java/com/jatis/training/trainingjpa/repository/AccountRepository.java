package com.jatis.training.trainingjpa.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.jatis.training.trainingjpa.entity.AccountEntity;

public interface AccountRepository extends PagingAndSortingRepository<AccountEntity, String> {

	@Query("select sum(a.balance) from AccountEntity a "
			+ "where a.customer.custNumber = :custNo")
	public BigDecimal totalBalance(@Param("custNo") String customerNo);
}
