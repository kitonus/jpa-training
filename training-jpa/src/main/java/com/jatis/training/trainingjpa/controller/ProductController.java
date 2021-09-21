package com.jatis.training.trainingjpa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.training.trainingjpa.entity.ProductEntity;
import com.jatis.training.trainingjpa.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	public ProductEntity save(@RequestBody ProductEntity product) {
		return service.save(product);
	}
	
	@GetMapping("/{code}")
	public ProductEntity findOne(@PathVariable String code) {
		return service.findOne(code);
	}
	
	@GetMapping("/all/{page}/{pageSize}")
	public Page<ProductEntity> findAll(@PathVariable int page, @PathVariable int pageSize){
		return service.findAll(page, pageSize);
	}
	
	@DeleteMapping("/{code}")
	public String delete(@PathVariable String code) {
		return service.delete(code);
	}
	
	@GetMapping("/endingwith/{name}/{page}/{pageSize}")
	public Page<ProductEntity> findByNameEndingWith(@PathVariable String name
			, @PathVariable int page, @PathVariable int pageSize){
		return service.findByNameEndingWith(name, page, pageSize);
	}
	
	@GetMapping("/bybranch/{branchId}/{page}/{pageSize}")
	public Page<ProductEntity> findByBranch(@PathVariable String branchId
			, @PathVariable int page, @PathVariable int pageSize){
		return service.findByBranch(branchId, page, pageSize);
	}
	
	@GetMapping("/bybranch/{branchId}")
	public List<ProductEntity> findByBranch(@PathVariable String branchId){
		return service.findByBranch(branchId);
	}
	
	@PostMapping("/updateexpiration/{code}/{expDate}")
	public ProductEntity updateExpiration(@PathVariable String code, @PathVariable String expDate) throws ParseException {
		return service.changeExpiration(code, new SimpleDateFormat("yyMMdd").parse(expDate));
	}
	
	@PostMapping("/setexpiredtoday/{branchId}")
	public int setExpiredToday(@PathVariable String branchId) {
		return service.setExpiredToday(branchId);
	}
}
