package com.cg.eshop.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.Customer;

@Repository
public interface ICustomer extends JpaRepository<Customer, Integer>{
	

}
