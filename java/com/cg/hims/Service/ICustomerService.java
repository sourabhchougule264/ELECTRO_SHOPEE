package com.cg.hims.Service;

import java.util.List;

import com.cg.hims.Entities.Customer;
import com.cg.hims.Exceptions.CustomerNotFound;

public interface ICustomerService {
	
	public Customer addCustomer(Customer cust);
	public Customer updateCustomer(int id,Customer cust) throws CustomerNotFound;
	public int removeCustomer(int Cid,Customer cust) throws CustomerNotFound;
	public Customer viewCustomer(int Cid,Customer cust) throws CustomerNotFound;
    public List<Customer> viewAllCustomers(String location);
}
