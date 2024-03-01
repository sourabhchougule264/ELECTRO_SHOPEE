package com.cg.hims.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.hims.Entities.Product;

@Repository
public interface IProductRepositary extends JpaRepository<Product,Integer>
{
	@Query("select p FROM Product p join fetch p.category g where g.categoryName=:c")
	public List<Product> findAllProductByCategory(@Param("c") String cname);
	
	@Modifying
	@Query("Delete FROM Product p WHERE p.productid=:b")
	public int DeleteProductbypid(@Param("b") int pid);
}
