package com.jatis.training.trainingjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jatis.training.trainingjpa.entity.ProductEntity;
import com.jatis.training.trainingjpa.repository.ProductRepository;

@Component
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	@Transactional(rollbackFor = Throwable.class)
	public ProductEntity save(ProductEntity product) {
		ProductEntity savedProduct = repo.findById(product.getProductCode()).orElse(null);
		if (savedProduct == null) {
			savedProduct = product;
		}
		if (product.getName() != null) {
			savedProduct.setName(product.getName());
		}
		if (product.getExpiration() != null) {
			savedProduct.setExpiration(product.getExpiration());
		}
		return repo.save(savedProduct);
	}
	
	@Transactional(readOnly = true)
	public ProductEntity findOne(String code) {
		return repo.findById(code).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Page<ProductEntity> findAll(int page, int pageSize){
		Pageable pageable = PageRequest.of(page, pageSize, 
				Sort.by(Order.desc("expiration"), Order.asc("name")));
		return repo.findAll(pageable);
	}
	
	@Transactional(rollbackFor = Throwable.class)
	public String delete(String code) {
		repo.deleteById(code);
		return code;
	}
	
	@Transactional(readOnly = true)
	public Page<ProductEntity> findByNameEndingWith(String name, int page, int pageSize){
		Pageable pageable = PageRequest.of(page, pageSize, 
				Sort.by(Order.desc("expiration"), Order.asc("name")));
		return repo.findByNameEndingWith(name, pageable);
	}
}
