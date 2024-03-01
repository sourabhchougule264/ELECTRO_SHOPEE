package com.cg.hims.Service;

import java.util.ArrayList;


import com.cg.hims.Entities.Cart;
import com.cg.hims.Entities.Product;
import com.cg.hims.Exceptions.ProductNotFoundException;

public interface ICartService {
	public Cart addProductTocart(int productid,int cartid,Cart cart,Product p, Integer quantity) throws ProductNotFoundException;
	public int removeProductFromcart(int productid,int cartid,Cart cart, Product p);
	public Cart updateProductQuantity(int cartid,int productid,Cart cart, Product p, int quantity) throws ProductNotFoundException;
	public String removeAllProducts(int cartid,Cart cart);
	public ArrayList<Cart> viewAllProducts(int cartid);
}
