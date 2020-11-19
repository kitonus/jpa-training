package com.jatis.training.trainingjpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.training.trainingjpa.entity.BranchEntity;
import com.jatis.training.trainingjpa.service.BranchService;

@RestController
public class BranchController extends BaseController{

	@Autowired
	private BranchService service;
	
	@GetMapping("/all/{page}/{pageSize}")
	public Page<BranchEntity> getAll(@PathVariable("page") int page, 
			@PathVariable("pageSize") int pageSize){
		return service.getAll(page, pageSize, "name");
	}
	
	@GetMapping("/{id}")
	public BranchEntity findOne(@PathVariable("id") String id) {
		return service.findOne(id);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	@PostMapping
	public BranchEntity save(@Valid @RequestBody BranchEntity branch) {
		return service.save(branch);
	}
	
	@GetMapping("/byname/{name}/{page}/{pageSize}")
	public Page<BranchEntity> findByName(@PathVariable("name") String name,
			@PathVariable("page") int page, @PathVariable("pageSize") int pageSize){
		return service.findByName(name, page, pageSize);
	}
}
