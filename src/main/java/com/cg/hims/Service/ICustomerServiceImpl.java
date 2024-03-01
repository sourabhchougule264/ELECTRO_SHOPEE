package com.cg.hims.Service;

/* AUTHER= SOURABH APPASAHEB CHOUGULE
 * DATE=27/11/2021
 * DESCRIPTION= CUSTOMER SERVICE LAYER
 */
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.Entities.Customer;
import com.cg.hims.Exceptions.CustomerNotFound;
import com.cg.hims.Repository.ICustomerRepository;

@Service
@Transactional
public class ICustomerServiceImpl implements ICustomerService{
     
	
	@Autowired
	ICustomerRepository cust_Repo;
	
/****************************************************************************************************************/
	@Override
	public Customer addCustomer(Customer cust) {
		// TODO Auto-generated method stub
		Customer cust_addCustomer=new Customer();
		cust_addCustomer.setCustomerid(cust.getCustomerid());
		cust_addCustomer.setFirstName(cust.getFirstName());
		cust_addCustomer.setLastName(cust.getLastName());
		cust_addCustomer.setMobileNumber(cust.getMobileNumber());
		cust_addCustomer.setEmail(cust.getEmail());
		cust_addCustomer=cust_Repo.save(cust);
		return cust_addCustomer;
	}
/****************************************************************************************************************/
	@Override
	public Customer updateCustomer(int id,Customer cust) throws CustomerNotFound{
		// TODO Auto-generated method stub
		Customer cust_addCustomer=cust_Repo.findById(id).orElseThrow(()-> new CustomerNotFound("Customer id not matched"));
		cust_addCustomer.setCustomerid(cust.getCustomerid());
		cust_addCustomer.setFirstName(cust.getFirstName());
		cust_addCustomer.setLastName(cust.getLastName());
		cust_addCustomer.setMobileNumber(cust.getMobileNumber());
		cust_addCustomer.setEmail(cust.getEmail());
		cust_addCustomer.setAddress(cust.getAddress());
		cust_addCustomer.setCart(cust.getCart());
		
//		if (!cust_Repo.existsById(cust.getCustomerid())) {
//			throw new CustomerNotFound("Oops!!!,unable to update,please enter valid id");
//		}
		cust_addCustomer=cust_Repo.save(cust);
		return cust_addCustomer;
	}

/****************************************************************************************************************/
	@Override
	public int removeCustomer(int Cid ,Customer cust) throws CustomerNotFound {
		// TODO Auto-generated method stub
//		if (!cust_Repo.existsById(cust.getCustomerid())) 
//		{
//			throw new CustomerNotFound("Oops!!!,unable to remove,please enter valid id");
//		}
		 cust_Repo.deleteById(Cid);
		 return 1;
	}

/****************************************************************************************************************/
	@Override
	public Customer viewCustomer(int Cid,Customer cust) throws CustomerNotFound
	{
		// TODO Auto-generated method stub
//		if (!cust_Repo.existsById(cust.getCustomerid())) 
//		{
//			throw new CustomerNotFound("Oops!!!,customer not found please enter valid id");
//		}
		return cust_Repo.viewCustomer(Cid);
	}

/****************************************************************************************************************/
	@Override
	public List<Customer> viewAllCustomers(String location) {
		// TODO Auto-generated method stub
		
		List<Customer> view_all=cust_Repo.findAllCustomers(location);
		return view_all;
	}
}
