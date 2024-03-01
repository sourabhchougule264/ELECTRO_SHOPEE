package com.cg.hims.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.hims.Entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer>

{
	
	@Query("select c FROM Customer c WHERE c.customerid=:s")
	public Customer viewCustomer(@Param("s") int Cid);
	
	@Query("select c FROM Customer c join fetch c.address a where a.city=:x")
	public List<Customer> findAllCustomers(@Param("x")  String location);

	@Modifying
	@Query("Delete FROM Customer c WHERE c.customerid=:y")
	public int deleteById(@Param("y") int Cid);
}
