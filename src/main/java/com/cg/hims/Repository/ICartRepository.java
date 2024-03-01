package com.cg.hims.Repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.hims.Entities.Cart;
import com.cg.hims.Entities.Product;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Integer> 
{
	
	//@Query("select p FROM Cart c JOIN Product p ON p.productid = c.productid WHERE c.cartid=: boo")
	//@Query("select c FROM Cart c INNER JOIN c.product p ON c.product=p.product=:boo")
	//@Query("delete FROM Cart c left join product p on c.cartid=p.productid=:boo")
	
	@Modifying
	@Query("delete FROM Cart c WHERE EXISTS(select p FROM Product p WHERE p.productid=:boo AND c.cartid=:kee)")
    int removeProductFromcart(@Param("boo") int id,@Param("kee") int cartid);
	
	@Query("select c FROM Cart c WHERE c.cartid=:zoo")
	public ArrayList<Cart> viewAllProducts(@Param("zoo") int id);
	
	@Modifying
	@Query("delete FROM Cart c WHERE EXISTS(select p FROM Product p) AND c.cartid=:bee")
	public int removeAllProducts(@Param("bee") int cartid);

}
