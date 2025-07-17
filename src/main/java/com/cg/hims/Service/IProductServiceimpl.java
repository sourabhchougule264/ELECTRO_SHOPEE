package com.cg.hims.Service;

/* AUTHER= SOURABH APPASAHEB CHOUGULE
 * DATE=27/11/2021
 * DESCRIPTION= PRODUCT SERVICE LAYER
 */

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.Entities.Product;
import com.cg.hims.Exceptions.ProductNotFoundException;
import com.cg.hims.Repository.IProductRepositary;

@Service
@Transactional
public class IProductServiceimpl implements IProductService {

	@Autowired
	IProductRepositary pro_repo;

	/***********************************************************************************************************/

	@Override
	public List<Product> viewAllProducts() {
		List<Product> prod_viewlist = pro_repo.findAll();
		return prod_viewlist;
	}

	/***********************************************************************************************************/
	@Override
	public Product addProduct(Product product) {

		Product addProd = new Product();
		addProd.setProductid(product.getProductid());
		addProd.setProductName(product.getProductName());
		addProd.setPrice(product.getPrice());
		addProd.setColor(product.getColor());
		addProd.setDimension(product.getDimension());
		addProd.setManufacturer(product.getManufacturer());
		addProd.setQuantity(product.getQuantity());
		addProd.setSpecification(product.getSpecification());
		addProd.setCategory(product.getCategory());
		addProd.setCart(product.getCart());
		addProd.setOrder(product.getOrder());
		Product prod_add = pro_repo.save(product);
		return prod_add;
	}

	/***********************************************************************************************************/
	@Override
	public Product updateProduct(int id, Product product) throws ProductNotFoundException {

		Product addProd = pro_repo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product id doesnt match ...!!!!!!Unable to update"));
		addProd.setProductName(product.getProductName());
		addProd.setPrice(product.getPrice());
		addProd.setColor(product.getColor());
		addProd.setDimension(product.getDimension());
		addProd.setManufacturer(product.getManufacturer());
		addProd.setQuantity(product.getQuantity());
		addProd.setSpecification(product.getSpecification());
		addProd.setCategory(product.getCategory());
		addProd.setCart(product.getCart());
		addProd.setOrder(product.getOrder());

		Product prod_add = pro_repo.save(product);

		return prod_add;
	}

	/***********************************************************************************************************/
	@Override
	public Optional<Product> viewProduct(int id) throws ProductNotFoundException {

		if (!pro_repo.existsById(id)) {
			throw new ProductNotFoundException("Ooops!!! product not found,please Enter valid Id");
		}
		return pro_repo.findById(id);
	}

	/***********************************************************************************************************/
	@Override
	public List<Product> viewAllProductByCategory(String cname) {

		List<Product> prod_viewall = pro_repo.findAllProductByCategory(cname);

		return prod_viewall;
	}

	/***********************************************************************************************************/
	@Override
	public int removeProduct(int pid) throws ProductNotFoundException {

		if (!pro_repo.existsById(pid)) {
			throw new ProductNotFoundException("Unable to delete, please provide valid pid");
		}
		pro_repo.DeleteProductbypid(pid);
		return 1;
	}
}