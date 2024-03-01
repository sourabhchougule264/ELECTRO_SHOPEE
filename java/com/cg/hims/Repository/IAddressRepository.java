package com.cg.hims.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.hims.Entities.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address,Integer>
{    

	@Query("select a FROM Address a WHERE a.addressid=:m")
	public Address findAllById(@Param("m") int id);
	
	@Query("select a FROM Address a WHERE a.addressid=:j")
	public Address viewAddress(@Param("j") int Aid );
	
	@Transactional
	@Modifying
	@Query("Delete FROM Address a WHERE a.addressid=:k")
	public int removeAddress(@Param("k") int Aid);
}
