package com.jatis.training.trainingjpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.training.trainingjpa.entity.CustomerEntity;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, String> {

	List<CustomerEntity> findByNameStartingWith(String prefix, Pageable pageable);
	
	long countByNameStartingWith(String prefix);
}
