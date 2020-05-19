package com.jatis.training.trainingjpa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.training.trainingjpa.entity.CustomerEntity;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, String> {

	Page<CustomerEntity> findByNameStartingWith(String prefix, Pageable pageable);
	
	Page<CustomerEntity> findAll(Pageable pageable);
	
	long countByNameStartingWith(String prefix);
}
