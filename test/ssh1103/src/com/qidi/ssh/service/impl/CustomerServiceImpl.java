package com.qidi.ssh.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qidi.ssh.dao.CustomerDao;
import com.qidi.ssh.pojo.Customer;
import com.qidi.ssh.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<Customer> list() {
		return customerDao.list();
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	

}
