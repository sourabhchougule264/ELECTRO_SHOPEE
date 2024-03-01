package com.cg.hims.Service;

/* AUTHER= SOURABH APPASAHEB CHOUGULE
 * DATE=27/11/2021
 * DESCRIPTION= ORDER SERVICE LAYER
 */
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.Entities.Address;
import com.cg.hims.Entities.Order;
import com.cg.hims.Exceptions.OrderNotFound;
import com.cg.hims.Repository.IAddressRepository;
import com.cg.hims.Repository.ILoginRepository;
import com.cg.hims.Repository.IOrderRepository;

@Service
@Transactional
public class IOrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository ord_Repo;
	
	@Autowired
	IAddressRepository Add_repo;
	
	@Autowired
	ILoginRepository log_repo;
/*****************************************************************************************/	
	@Override
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub
		Order add_Ord=new Order();
		add_Ord.setAddress(order.getAddress());
		add_Ord.setCustomer(order.getCustomer());
		add_Ord.setOrderDate(order.getOrderDate());
		add_Ord.setOrderid(order.getOrderid());
		add_Ord.setOrderStatus(order.getOrderStatus());
		add_Ord.setProduct(order.getProduct());
		
		add_Ord=ord_Repo.save(order);
		return add_Ord;
	}

/*****************************************************************************************/	
	@Override
	public Order updateOrder(int id,Order order) throws OrderNotFound
	{
		// TODO Auto-generated method stub
		
		Order add_Ord=ord_Repo.findById(id).orElseThrow(()-> new OrderNotFound("order doesnt match"));
		add_Ord.setAddress(order.getAddress());
		add_Ord.setCustomer(order.getCustomer());
		add_Ord.setOrderDate(order.getOrderDate());
		add_Ord.setOrderid(order.getOrderid());
		add_Ord.setOrderStatus(order.getOrderStatus());
		add_Ord.setProduct(order.getProduct());
//		if (!ord_Repo.existsById(order.getOrderid())) 
//		{
//			throw new OrderNotSupport("Oops!!!,unable to add , enter valid id");
//		}
		add_Ord=ord_Repo.save(order);
		return ord_Repo.save(order);
	}

/*****************************************************************************************/	
	@Override
	public int removeOrder(int Oid,Order order) throws OrderNotFound {
		// TODO Auto-generated method stub
//		if (!ord_Repo.existsById(order.getOrderid())) 
//		{
//			throw new OrderNotFound("ooPS!!!,unable to remove,please give valid id");
//		}
		 ord_Repo.deleteById(Oid);
		 return 1;
	}

/*****************************************************************************************/	
	@Override
	public Optional<Order> viewOrder(int id,Order order) throws OrderNotFound {
		// TODO Auto-generated method stub
//		if (!ord_Repo.existsById(order.getOrderid()))
//		{
//			throw new OrderNotFound("Ooops!!!,order not found , enetr valid id");
//		}
		return ord_Repo.findById(id);
	}

/*****************************************************************************************/	
	@Override
	public List<Order> viewAllOrders(LocalDate date) {
		// TODO Auto-generated method stub
		List<Order> view_all=ord_Repo.findAllByDate(date);
		return view_all;
	}

/*****************************************************************************************/	
	@Override
	public List<Order> viewAllOrdersByLocation(int id,Optional<Address> loc) {
		// TODO Auto-generated method stub
		loc=Add_repo.findById(id);
		List<Order> view_all_loc=ord_Repo.findAllByLocation(loc);
		return view_all_loc;
	}

/*****************************************************************************************/	
	@Override
	public List<Order> viewAllOrdersUserId(String userid) {
		// TODO Auto-generated method stub
		
		List<Order> view_all_Id=ord_Repo.findAllByUserid(userid);
		return view_all_Id;
	}
}
