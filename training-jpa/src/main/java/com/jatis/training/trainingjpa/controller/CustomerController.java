package com.jatis.training.trainingjpa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.training.trainingjpa.dto.CustomerBalanceDTO;
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
	
	@GetMapping({"/list_all/{page}/{pageSize}", "/list_all/{page}"})
	public Page<CustomerEntity> listAll(@PathVariable("page") int page
			, @PathVariable(value="pageSize", required = false) Optional<Integer> pageSize) {
		return custService.getAll(page, pageSize.orElse(10));
	}
	@GetMapping("/list/{prefix}/{page}")
	public Page<CustomerEntity> listByNamePrefix(@PathVariable("prefix") String prefix,
			int page) {
		return custService.getCustomersByName(prefix, page, 10);
	}
	
	@GetMapping("/balance/{custNo}")
	public CustomerBalanceDTO getBalance(@PathVariable("custNo") String customerNo) {
		return custService.getTotalBalance(customerNo);
	}
	
	@GetMapping("/count/{namePrefix}")
	public long countByCustomerNamePrefix(@PathVariable String namePrefix) {
		return custService.countCustomersByName(namePrefix);
	}
	
	@DeleteMapping
	public int deleteCustomersWithIdsIn(@RequestBody String[] ids) {
		return custService.deleteCustomersWithIdsIn(ids);
	}
	
	@PostMapping("/dateOfBirth/{dateStr}/{branchId}")
	public int updateDateOfBirth(@PathVariable String dateStr,
			@PathVariable String branchId) throws ParseException {
		return custService.updateDateOfBirth(new SimpleDateFormat("yyMMdd").parse(dateStr), branchId);
	}
}
