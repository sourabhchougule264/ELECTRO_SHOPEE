package com.cg.hims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.Entities.Cart;
import com.cg.hims.Entities.Product;
import com.cg.hims.Exceptions.ProductNotFoundException;
import com.cg.hims.Service.ICartServiceImpl;
import com.cg.hims.Service.IProductServiceimpl;

@RestController
@RequestMapping("/rest/auth/cart")
public class CartController
{
	@Autowired
	ICartServiceImpl cartserviceobj;
	
	@Autowired
	IProductServiceimpl prodserviceobj;
	/**
 * @throws ProductNotFoundException *****************************************************************************************************************************************/	
	@PostMapping("/addprodtocart/{id}/{ctid}")
	public ResponseEntity<Cart> addProductTocart(@PathVariable("id") int productid,@PathVariable("ctid") int cartid ,Cart cart,Product p,Integer quantity) throws ProductNotFoundException
	{
		if (prodserviceobj.viewProduct(productid).isPresent())
		{        
        	cart=cartserviceobj.addProductTocart(productid, cartid, cart, p, quantity);
   			return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		   
		}
		else {
			return new ResponseEntity("Product not found unable to add to cart", HttpStatus.NOT_FOUND);
		}
		
	}
/*******************************************************************************************************************************************/
	@DeleteMapping("/deleteoneproduct/{ctid}/{id}")
	public String removeProductFromcart(@PathVariable("ctid") int cartid,@PathVariable("id") int id,Cart cart,Product p)
	{
//		if (((Product) cart.getProduct().get(id)).getProductid()==0) 
//		{
//			return "Product not found";
//		}
		cartserviceobj.removeProductFromcart(id, cartid, cart, p);
		return "Product Successfully removed from cart";
		
	}
/*******************************************************************************************************************************************/
	@PutMapping("/update/{id}/{cid}/{quant}")
	public ResponseEntity<Cart> updateProductQuantity(@PathVariable("id") int productid,@PathVariable("cid") int cartid,Cart cart,Product p,@PathVariable("quant") int quantity) throws ProductNotFoundException
	{
		if (cart.getProduct().contains(p.getProductid()))
		{
			cart=cartserviceobj.updateProductQuantity(cartid, productid, cart, p, quantity);
			return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Product not found unable to add to cart", HttpStatus.NOT_FOUND);
		}
				
	}
/*******************************************************************************************************************************************/
	@DeleteMapping("/removeAll/{id}")
	public String removeAllProducts(@PathVariable("id") int cartid,Cart cart)
	{
//		if (cart.getProduct()==null) 
//		{
//			return "Cart is Already Empty";
//		}
		cartserviceobj.removeAllProducts(cartid, cart);
		return "NOW cart is empty";
		
	}
/*******************************************************************************************************************************************/
	@GetMapping("/viewAll/{id}")
	public ResponseEntity<List<Cart>> viewAllProducts(@PathVariable("id") int id ,Cart cart)
	{
		
//		if (cart.getProduct().isEmpty()) 
//		{
//			return new ResponseEntity("Cart is Empty", HttpStatus.NOT_FOUND);
//		}
		List<Cart> viewAlList = cartserviceobj.viewAllProducts(id);
		return new ResponseEntity<List<Cart>>(viewAlList,HttpStatus.OK);
		
	}
	
}
