package com.jatis.training.trainingjpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.training.trainingjpa.entity.AccountEntity;
import com.jatis.training.trainingjpa.service.CustomerService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private CustomerService custService;
	
	@PostMapping
	public AccountEntity save(@Valid @RequestBody AccountEntity account) {
		return custService.saveAccount(account);
	}
	
	@GetMapping("/{page}/{pageSize}")
	public Page<AccountEntity> findAll(@PathVariable("page") int page,
				@PathVariable("pageSize") int pageSize){
		return custService.findAllAccounts(page, pageSize);
	}
}
