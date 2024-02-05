package com.customer.DAO;

import java.util.List;

import com.customer.model.Customer;

public interface CustomerDAO {

	int addCustomer(Customer c);
	int updateCustomer(Customer c);
	List<Customer> getCustomerByFirstName(String fname);
	List<Customer> getCustomerByCity(String city);
	List<Customer> getCustomerByEmail(String email);
	List<Customer> getCustomerByPhone(String phone);
	List<Customer> getAllCustomer();
	Customer getCustomerById(String id);
	int deleteCustomer(String id);
	
}
