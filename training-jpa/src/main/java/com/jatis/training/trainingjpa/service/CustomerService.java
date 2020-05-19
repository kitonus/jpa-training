package com.jatis.training.trainingjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jatis.training.trainingjpa.entity.CustomerEntity;
import com.jatis.training.trainingjpa.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Transactional(rollbackFor = Throwable.class)
	public CustomerEntity saveCustomer(CustomerEntity customer) {
		CustomerEntity fromDb = customerRepo.findById(customer.getCustNumber()).orElse(null);
		if (fromDb == null) {
			return customerRepo.save(customer);
		} 
		if (customer.getName() != null) {
			fromDb.setName(customer.getName());
		}
		if (customer.getBirthDate() != null) {
			fromDb.setBirthDate(customer.getBirthDate());
		}
		return fromDb;
	}
	
	@Transactional(readOnly = true)
	public Page<CustomerEntity> getCustomersByName(String prefix, int page, int pageSize) {
		return customerRepo.findByNameStartingWith(prefix, 
				PageRequest.of(page, pageSize, Sort.by(Direction.ASC, "name", "custNumber")));
	}
	
	@Transactional(readOnly = true)
	public Page<CustomerEntity> getAll(int page, int pageSize) {
		return customerRepo.findAll( 
				PageRequest.of(page, pageSize, Sort.by(Direction.ASC, "name", "custNumber")));
	}
	
	@Transactional(readOnly = true)
	public long countCustomersByName(String prefix) {
		return customerRepo.countByNameStartingWith(prefix);
	}
}
