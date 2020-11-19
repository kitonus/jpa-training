package com.jatis.training.trainingjpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.training.trainingjpa.entity.BranchEntity;

public interface BranchRepository extends PagingAndSortingRepository<BranchEntity, String> {

	public Page<BranchEntity> findByNameStartsWithIgnoreCase(String name, Pageable pageable);
}
