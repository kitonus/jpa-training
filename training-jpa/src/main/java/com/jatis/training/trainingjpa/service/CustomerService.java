package com.jatis.training.trainingjpa.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jatis.training.trainingjpa.dto.CustomerBalanceDTO;
import com.jatis.training.trainingjpa.entity.AccountEntity;
import com.jatis.training.trainingjpa.entity.CustomerEntity;
import com.jatis.training.trainingjpa.repository.AccountRepository;
import com.jatis.training.trainingjpa.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
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
		if (customer.getBranch() != null) {
			fromDb.setBranch(customer.getBranch());
		}
		//save tidak perlu dipanggil karena object fromDb sudah tersambung ke DB!
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
	
	@Transactional(rollbackFor = Throwable.class)
	public AccountEntity saveAccount(AccountEntity account) {
		AccountEntity fromDb = accountRepo.findById(account.getAccountNo()).orElse(null);
		if (fromDb == null) {
			return accountRepo.save(account);
		}
		if (account.getBalance() != null) {
			fromDb.setBalance(account.getBalance());
		}
		if (account.getCustomer() != null) {
			fromDb.setCustomer(account.getCustomer());
		}
		return fromDb;
	}
	
	@Transactional(readOnly = true)
	public Page<AccountEntity> findAllAccounts(int page, int pageSize){
		return accountRepo.findAll(PageRequest.of(page, pageSize, 
				Sort.by(Direction.ASC, "accountNo")));
	}
	
	@Transactional(readOnly = true)
	public CustomerBalanceDTO getTotalBalance(String customerNo) {
		return new CustomerBalanceDTO(customerRepo.findById(customerNo).orElse(null), 
				accountRepo.totalBalance(customerNo));
	}
	
	@Transactional(rollbackFor = Throwable.class)
	public int deleteCustomersWithIdsIn(String[] ids) {
		return customerRepo.deleteByCustomerWithIdIn(ids);
		
	}
	
	@Transactional(rollbackFor = Throwable.class)	
	public int updateDateOfBirth(Date dob, String branchId) {
		return customerRepo.updateDateOfBirth(dob, branchId);
	}
}
