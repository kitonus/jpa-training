package com.jatis.training.trainingjpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.training.trainingjpa.entity.ProductEntity;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, String> {

	public Page<ProductEntity> findByNameEndingWith(String name, Pageable pageable);
}
