package com.jatis.training.trainingjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jatis.training.trainingjpa.entity.BranchEntity;
import com.jatis.training.trainingjpa.repository.BranchRepository;

@Service
public class BranchService {

	@Autowired
	private BranchRepository repo;
	
	@Transactional(readOnly = true, rollbackFor = Throwable.class)
	public Page<BranchEntity> getAll(int page, int pageSize, String sortByColumn){
		Pageable pageRequest = PageRequest.of(page, pageSize, Direction.ASC, 
				sortByColumn);
		return repo.findAll(pageRequest);
	}
	
	@Transactional(readOnly = true, rollbackFor = Throwable.class)
	public BranchEntity findOne(String id) {
		return repo.findById(id).orElse(null);
	}
	
	@Transactional(rollbackFor = Throwable.class)
	public BranchEntity save(BranchEntity branch) {
		BranchEntity savedBranch = repo.findById(branch.getBranchId()).orElse(null);
		if (savedBranch != null) {
			savedBranch.setAddress(branch.getAddress());
			savedBranch.setName(branch.getName());
			savedBranch.setUpdatedDateTime(branch.getCreateDateTime());
			savedBranch.setUpdatedBy(branch.getCreatedBy());
			
			//no need to explicitly call repo.save() because savedBranch is already persistent
			return savedBranch;
		} else {
			return repo.save(branch);
		}
	}
	
	@Transactional(readOnly = true, rollbackFor = Throwable.class)
	public Page<BranchEntity> findByName(String name, int page, int pageSize){
		Pageable pageable = PageRequest.of(page, pageSize, 
				Sort.by("name", "address"));
		return repo.findByNameStartsWithIgnoreCase(name, pageable );
	}
}
