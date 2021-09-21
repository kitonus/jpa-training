package com.jatis.training.trainingjpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.jatis.training.trainingjpa.entity.ProductEntity;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, String> {

	public Page<ProductEntity> findByNameEndingWith(String name, Pageable pageable);
	
	@Query(value = "from ProductEntity pe where pe.creatorBranch.branchId = :branchId",
			countQuery = "select count(pe.productCode) from ProductEntity pe where pe.creatorBranch.branchId = :branchId")
	public Page<ProductEntity> findByBranch(@Param("branchId") String branchId, Pageable pageable);

	@Query(value = "from ProductEntity pe where pe.creatorBranch.branchId = :branchId")
	public List<ProductEntity> findByBranch(@Param("branchId") String branchId);
	
	@Modifying
	@Query("update ProductEntity pe set pe.expiration = :exp where pe.productCode in "
			+ " (select p.productCode from ProductEntity p where p.creatorBranch.branchId = :brId)")
	public int setExpired(@Param("exp") Date exp, @Param("brId") String branchId);
}
