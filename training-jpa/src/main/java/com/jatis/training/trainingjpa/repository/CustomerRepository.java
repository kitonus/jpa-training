package com.jatis.training.trainingjpa.repository;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.jatis.training.trainingjpa.entity.CustomerEntity;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, String> {

	Page<CustomerEntity> findByNameStartingWith(String prefix, Pageable pageable);
	
	Page<CustomerEntity> findAll(Pageable pageable);
	
	long countByNameStartingWith(String prefix);
	
	@Modifying
	@Query("delete CustomerEntity where id in :ids")
	int deleteByCustomerWithIdIn(@Param("ids") String[] ids);
	
	@Modifying
	@Query("update CustomerEntity c set c.birthDate = :dob where"
			+ " c.id in (select c1.id from CustomerEntity c1 where c1.branch.id = :branchId)")
	int updateDateOfBirth(@Param("dob") Date dob, @Param("branchId") String branchId);
}
