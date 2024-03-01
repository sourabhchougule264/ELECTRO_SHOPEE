package com.cg.hims.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cg.hims.Entities.Address;
import com.cg.hims.Entities.Order;
import com.cg.hims.Exceptions.OrderNotFound;




public interface IOrderService {
	
	public Order addOrder(Order order);
	public Order updateOrder(int id,Order order) throws OrderNotFound;
	public int removeOrder(int Oid,Order order) throws OrderNotFound;
	public Optional<Order> viewOrder(int id,Order order) throws OrderNotFound;
	public List<Order> viewAllOrders(LocalDate date);
	public List<Order> viewAllOrdersByLocation(int id,Optional<Address> loc);
	public List<Order> viewAllOrdersUserId(String userid);

}
