package com.cg.hims.controller;

/* AUTHER= SOURABH APPASAHEB CHOUGULE
 * DATE=27/11/2021
 * DESCRIPTION= PRODUCT CONTROLLER LAYER
 */

import java.util.List;
import java.util.Optional;

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

import com.cg.hims.Entities.Product;
import com.cg.hims.Exceptions.ProductNotFoundException;
import com.cg.hims.Service.IProductServiceimpl;

@RestController
@RequestMapping("/Product")
public class ProductController 
  {
    
	@Autowired
	IProductServiceimpl Proserviceobj;
    
/************************************************************************************************/
	
     //view All Products list    
	@GetMapping(path = "/viewAllProducts")
	public ResponseEntity<List<Product>> viewAllProducts()
	{  
		List<Product> productImpl=Proserviceobj.viewAllProducts();
		if(productImpl.isEmpty())
		{
			return new ResponseEntity("product not found",HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity(productImpl,HttpStatus.OK);		
	}
	
/**********************************************************************************************/
	//Adding or creating  Product
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{  
		if(product.getProductid()==0)
		{
			return new ResponseEntity("Invalid Id",HttpStatus.NOT_FOUND);
		}
		Product proImpl=Proserviceobj.addProduct(product);
		
		return new ResponseEntity(proImpl,HttpStatus.OK);

	}
/***********************************************************************************************/
	// Updating Product
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") int id ,@RequestBody Product product) throws ProductNotFoundException
	{     if(product==null)
	      {
		     return new ResponseEntity("Enter valid product details",HttpStatus.NOT_FOUND);
	      }
	Product prod=Proserviceobj.updateProduct(id, product);
		return new ResponseEntity(prod,HttpStatus.OK);	
	}
/************************************************************************************************/
	// find product by Id
	
	@GetMapping("/viewProduct/{id}")
	public ResponseEntity<Optional<Product>> viewProduct(@PathVariable ("id") int id) throws ProductNotFoundException
	{   
		Optional<Product> productIn=Proserviceobj.viewProduct(id);
		if(productIn==null)
		{
			return new ResponseEntity("Enter valid Id",HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity(productIn,HttpStatus.OK);
	}
/************************************************************************************************/
	//find all product by category
	
	@GetMapping("/viewAllProductByCategory/{categoryName}")
	public ResponseEntity<List<Product>> viewAllProductByCategory(@PathVariable("categoryName") String cname)
	{
		List<Product> productImpl=Proserviceobj.viewAllProductByCategory(cname);
		if(productImpl.isEmpty())
		{
			return new ResponseEntity("Products not found",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(productImpl,HttpStatus.OK);		
	}
/************************************************************************************************/
	// Delete the product
	
	@DeleteMapping("/removeProduct/{id}")
	public ResponseEntity<Product> removeProduct(@PathVariable("id") int pid) throws ProductNotFoundException
	{	
		int productdel=Proserviceobj.removeProduct(pid);
		return new ResponseEntity(productdel,HttpStatus.OK);
	}
}
