package com.qidi.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qidi.ssh.dao.CustomerDao;
import com.qidi.ssh.pojo.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	@Override
	public List<Customer> list() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	@Override
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	@Override
	public Customer findById(Long cust_id) {
		return  this.getHibernateTemplate().get(Customer.class, cust_id);
	}

	@Override
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

}
