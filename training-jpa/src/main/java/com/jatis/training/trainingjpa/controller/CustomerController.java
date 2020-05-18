package com.jatis.training.trainingjpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.training.trainingjpa.dto.CustomerListDto;
import com.jatis.training.trainingjpa.entity.CustomerEntity;
import com.jatis.training.trainingjpa.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController{

	@Autowired
	private CustomerService custService;
	
	@PostMapping
	public CustomerEntity saveCustomer(@Valid @RequestBody CustomerEntity customer) {
		return custService.saveCustomer(customer);
	}
	
	@GetMapping("/list/{prefix}/{page}")
	public CustomerListDto listByNamePrefix(@PathVariable("prefix") String prefix,
			int page) {
		return new CustomerListDto(custService.getCustomersByName(prefix, page, 10), 
				custService.countCustomersByName(prefix), page, 10);
	}
}
