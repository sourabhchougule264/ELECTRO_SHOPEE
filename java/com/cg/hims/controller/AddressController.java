package com.cg.hims.controller;



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

import com.cg.hims.Entities.Address;
import com.cg.hims.Exceptions.AddressNotFoundException;
import com.cg.hims.Service.IAddressServiceImpl;

@RestController
@RequestMapping("/rest/auth/Address")
public class AddressController 
{
	@Autowired
	IAddressServiceImpl addserviceobj;
	
/**************************************************************************************************************************************/	
	@PostMapping("/addAddress")
	public ResponseEntity<Address> addAddress(@RequestBody  Address add)
	{
		if (add.getAddressid()==0)
		{
			return new ResponseEntity("Invalid",HttpStatus.NOT_FOUND);
		}
		Address addimpl=addserviceobj.addAddress(add);
		return new ResponseEntity(addimpl,HttpStatus.OK);
		
	}
	
/**************************************************************************************************************************************/
	@PutMapping("/updateAddress/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable("id") int id, @RequestBody Address add) throws AddressNotFoundException
	{
		if (add==null)
		{
		     return new ResponseEntity("Invalid data",HttpStatus.NOT_FOUND);	
		}
		
		Address updateimplAddress=addserviceobj.updateAddress(id, add);
		return new ResponseEntity(updateimplAddress,HttpStatus.OK);
	}
	
/***********************************************id***************************************************************************************/
	@DeleteMapping("/removeAddress/{id}")
	public ResponseEntity<Address> removeAddress(@PathVariable("id") int Aid,Address add) throws AddressNotFoundException
	{
		int removeimpl=addserviceobj.removeAddress(Aid, add);
		return new ResponseEntity(removeimpl,HttpStatus.OK);
		
	}
	
/**************************************************************************************************************************************/
	@GetMapping("/viewAllAddress/{id}")
	public ResponseEntity<Address> viewAllAddress(@PathVariable int id)
	{
		Address viewimpl=addserviceobj.viewAllAddress(id);
		if (viewimpl==null)
		{
			return new ResponseEntity("Address not available",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(viewimpl,HttpStatus.OK);
		
	}
	
/**************************************************************************************************************************************/
	@GetMapping("/viewAddress/{id}")
	public ResponseEntity<Address> viewAddress(@PathVariable("id") int Aid, Address add) throws AddressNotFoundException
	{
		Address viewaddimpl=addserviceobj.viewAddress(Aid, add);
		if (viewaddimpl==null) 
		{
			return new ResponseEntity("Address not found",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(viewaddimpl,HttpStatus.OK);	
	}

}
