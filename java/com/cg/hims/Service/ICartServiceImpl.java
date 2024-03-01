package com.cg.hims.Service;


/* AUTHER= SOURABH APPASAHEB CHOUGULE
 * DATE=27/11/2021
 * DESCRIPTION= CART SERVICE LAYER
 */
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.hims.Entities.Cart;
import com.cg.hims.Entities.Product;
import com.cg.hims.Exceptions.ProductNotFoundException;
import com.cg.hims.Repository.ICartRepository;
import com.cg.hims.Repository.IProductRepositary;

@Service
@Transactional
public class ICartServiceImpl implements ICartService{
  
	    @Autowired
	    ICartRepository cart_repo;
	    
	    @Autowired
	    IProductRepositary pro_repo;

/**
 * @throws ProductNotFoundException *******************************************************************************************************************************************************/
	@Override
	public Cart addProductTocart(int productid,int cartid,Cart cart,Product p, Integer quantity) throws ProductNotFoundException {

		//List<Product> kList = new ArrayList<>();
		p=pro_repo.findById(productid).orElseThrow(()->  new ProductNotFoundException("Product you are searching ic currently unavailable....!!!") );		 
		cart=cart_repo.findById(cartid).get();
		 if(cart.getProduct().isEmpty())
		 {		 
			// p.setSelectedQuantity(cart.getpQuant());
			 //kList.add(p);
			 cart.getProduct().add(p);
			 
		 }
		 else if (!cart.getProduct().contains(p)) 
			 {
				 //cart.setCartid(cart.getCartid());
				 //p.setSelectedQuantity(cart.getpQuant());
				// kList.add(p);
				 cart.getProduct().add(p);
			 }
			 else {
				 	System.out.println("The product that you trying to add is already present in the Cart.....!!!!!");
			 	  }
			 Cart addprodtoCart = cart_repo.save(cart);
			 return addprodtoCart;
		 
		 
/*******************************************************************************************************************************************************************/
//			if (!kList.contains(p))
//			{ p=new Product();
//				p=pro_repo.findById(productid).orElseThrow(()-> new ProductNotFoundException("Product you are searching ic currently unavailable....!!!"));
//				kList.add(p);
//			}
		 
//	   boolean isPresent= false;
//		 for(int i=1;i<=kList.size();i++)
//		 { 		p=pro_repo.findById(productid).orElseThrow(()-> new ProductNotFoundException("Product you are searching ic currently unavailable....!!!"));	
//		 			if (!cart.getProduct().contains(p)) 
//		 			{
//		                kList.set(i, p);	
//						isPresent=true;
//						return cart;		    
//		            }
//		 			
//		 }
//		 if (isPresent=true) 
//			{
//				kList.add(p);
//				cart.setProduct(kList);
//			}
//			Cart addPtoC = cart_repo.save(cart);
//		 return addPtoC;
			 
/***********************************************************************************************************************************************************/		 
		 
//		 p=pro_repo.findById(productid).orElseThrow(()->  new ProductNotFoundException("Product you are searching ic currently unavailable....!!!") );		 
//		 
//			 new Product();
//			p=pro_repo.findById(productid).orElseThrow(()->  new ProductNotFoundException("Product you are searching ic currently unavailable....!!!") );
//			 kList.add(p);
//			 cart.setProduct(kList);
//		 
//		 Cart addProdCart = cart_repo.save(cart);
//		 
//		 return addProdCart;
		 
		 
		 
	}

/*********************************************************************************************************************************************************/
	@Override
	public int  removeProductFromcart(int productid,int cartid,Cart cart, Product p) {
		cart_repo.removeProductFromcart(productid, cartid);
		return 1;
	}

/*********************************************************************************************************************************************************/
	@Override
	public Cart updateProductQuantity(int cartid,int productid,Cart cart, Product p, int quantity) throws ProductNotFoundException
	{
		 cart=cart_repo.findById(cartid).get();
        List<Product> clist = new ArrayList<>();
        clist=cart.getProduct();
        for(Product cls : clist)
        {
        	int pdid=cls.getProductid();
        	if (pdid==productid)
        	{
        		cls.setSelectedQuantity(quantity);
				break;
			}
        }
		return cart;
	}

/*********************************************************************************************************************************************************/
	@Override
	public String removeAllProducts(int cartid,Cart cart) {
		// TODO Auto-generated method stub		
		cart_repo.removeAllProducts(cartid);
		return "cart is empty now";
	}

/*********************************************************************************************************************************************************/
	@Override
	public ArrayList<Cart> viewAllProducts(int cartid) {
		// TODO Auto-generated method stub
		ArrayList<Cart> cartListAll=cart_repo.viewAllProducts(cartid);
		return cartListAll;
	}

}
