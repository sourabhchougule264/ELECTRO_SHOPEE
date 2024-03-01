package com.cg.hims.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.hims.Entities.Address;
import com.cg.hims.Entities.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer>
{
	@Modifying
    @Query("Delete from Order o where o.orderid=:d")
	public int deleteById(@Param("d") int Oid);
    
//  @Query("select o from Order o where o.orderid=:f")
//	public Order findOrderById(@Param("f") Order order);

    @Query("select o from Order o where o.orderDate =:g")
	public List<Order> findAllByDate(@Param("g") LocalDate date);

    @Query("select o from Order o join fetch o.address a where a.city=:h")
	public List<Order> findAllByLocation(@Param("h") Optional<Address> loc);

    @Query("select o from Order o join fetch o.customer c join fetch c.user u where u.userid =:z")
	public List<Order> findAllByUserid(@Param("z") String userid);
    
}
