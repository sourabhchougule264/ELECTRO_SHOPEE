package com.cg.hims.Service;

import java.util.List;
import java.util.Optional;

import com.cg.hims.Entities.Product;
import com.cg.hims.Exceptions.ProductNotFoundException;

public interface IProductService {
	
	public List<Product> viewAllProducts();
	public Product addProduct(Product product);
	public Product updateProduct(int productid,Product product) throws ProductNotFoundException;
	public Optional<Product> viewProduct(int id) throws ProductNotFoundException;
	public List<Product> viewAllProductByCategory(String cname);
    public int removeProduct(int pid) throws ProductNotFoundException; 
}
