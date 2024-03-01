package com.cg.hims.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.Entities.Customer;
import com.cg.hims.Exceptions.CustomerNotFound;
import com.cg.hims.Service.ICustomerServiceImpl;


@RestController
@RequestMapping("/rest/auth/Customer")
public class CustomerController
{   
	@Autowired(required = false)
	ICustomerServiceImpl custserviceobj;
	
/************************************************************************************************************************/
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer cust)
	{
		if (cust.getCustomerid()==0) 
		{
			return new ResponseEntity("Invalid",HttpStatus.NOT_FOUND);	
		}
		
		Customer addimpl=custserviceobj.addCustomer(cust);
		
		return new ResponseEntity(addimpl,HttpStatus.OK);
	}
	
/************************************************************************************************************************/
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id,@RequestBody Customer cust) throws CustomerNotFound
	{
		if (cust==null)
		{
		    return new ResponseEntity("Unable to update",HttpStatus.NOT_FOUND);
		}
		Customer updateimpl=custserviceobj.updateCustomer(id, cust);
		return new ResponseEntity(updateimpl,HttpStatus.OK);
		
	}

/************************************************************************************************************************/
	@DeleteMapping("/removeCustomer/{id}")
	public ResponseEntity<Customer> removeCustomer(@PathVariable("id") int Cid,Customer cust) throws CustomerNotFound
	{
		int removeimpl=custserviceobj.removeCustomer(Cid, cust);
		
		return new ResponseEntity(removeimpl,HttpStatus.OK);
		
	}
	
/************************************************************************************************************************/
	@GetMapping("/viewCustomer/{id}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable("id") int Cid,Customer cust) throws CustomerNotFound
	{
		Customer viewimpl=custserviceobj.viewCustomer(Cid, cust);
		if (viewimpl==null)
		{
			return new ResponseEntity("Customer not found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(viewimpl,HttpStatus.OK);
		
	}
	
/************************************************************************************************************************/
    
	@GetMapping("/viewAllCustomers/{city}")
	public ResponseEntity<List<Customer>> viewAllCustomers(@PathVariable("city") String location)
    {
		List<Customer> viewallimpl=custserviceobj.viewAllCustomers(location);
		if (viewallimpl==null)
		{
			return new ResponseEntity("List of customers not available",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(viewallimpl,HttpStatus.OK);
    }
}
