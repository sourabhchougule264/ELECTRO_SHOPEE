package com.cg.hims.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.cg.hims.Entities.Order;
import com.cg.hims.Exceptions.OrderNotFound;
import com.cg.hims.Service.IOrderServiceImpl;

@RestController
@RequestMapping("/rest/auth/Order")
public class OrderController 
{
	@Autowired(required = false)
	IOrderServiceImpl ordserviceobj;
	
/*******************************************************************************************************************/
	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrder(@RequestBody Order order)
	{
		if (order.getOrderid()==0)
		{
			return new ResponseEntity("Invalid",HttpStatus.NOT_FOUND);
		}
		Order orderimpl=ordserviceobj.addOrder(order);
		return new ResponseEntity(orderimpl,HttpStatus.OK);
	}
	
/*******************************************************************************************************************/
	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") int id,@RequestBody Order order) throws OrderNotFound
	{
		if (order==null) 
		{
			return new ResponseEntity("Unable to upload",HttpStatus.NOT_FOUND);
		}
		Order updateimpl=ordserviceobj.updateOrder(id, order);
		return new ResponseEntity(updateimpl,HttpStatus.OK);		
	}
	
/*******************************************************************************************************************/
	@DeleteMapping("/removeOrder/{id}")
	public ResponseEntity<Order> removeOrder(@PathVariable("id") int Oid,Order order) throws OrderNotFound
	{
		int removeimpl=ordserviceobj.removeOrder(Oid, order);
		return new ResponseEntity(removeimpl,HttpStatus.OK);	
	}
	
/*******************************************************************************************************************/
	@GetMapping("/viewOrder/{id}")
	public ResponseEntity<Order> viewOrder(@PathVariable("id") int id,Order order) throws OrderNotFound
	{
		Optional<Order> viewimpl=ordserviceobj.viewOrder(id, order);
		if (viewimpl==null)
		{
			return new ResponseEntity("Order bot found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(viewimpl,HttpStatus.OK);
		
	}
	
/*******************************************************************************************************************/
	@GetMapping("/viewOrderByDate/{date}")
	public ResponseEntity<List<Order>> viewAllOrders(@PathVariable("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
	{
		List<Order> viewallimpl=ordserviceobj.viewAllOrders(date);
		if (viewallimpl==null) 
		{
			return new ResponseEntity("order list not found",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(viewallimpl,HttpStatus.OK);
		
	}
	
/*******************************************************************************************************************/
	@GetMapping("/viewOrderByLocation/{city}")
	public ResponseEntity<List<Order>> viewAllOrdersByLocation(@PathVariable("city") int id, Optional<Address> loc)
	{
		List<Order> viewallbylocimpl=ordserviceobj.viewAllOrdersByLocation(id, loc);
		if (viewallbylocimpl==null)
		{
			return new ResponseEntity("order list not available",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(viewallbylocimpl,HttpStatus.OK);	
	}
	
/*******************************************************************************************************************/
	@GetMapping("/viewOrderByUserId/{userId}")
	public ResponseEntity<List<Order>> viewAllOrdersUserId(@PathVariable("userId") String userid)
	{
		List<Order> viewallbyidimpl=ordserviceobj.viewAllOrdersUserId(userid);
		if (viewallbyidimpl==null) 
		{
			return new ResponseEntity("order list not available",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(viewallbyidimpl,HttpStatus.OK);	
	}
	
}
