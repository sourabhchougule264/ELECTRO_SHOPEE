package com.cg.hims.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Cart_Detail")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private int cartid;
	
	private int pQuant;
	
	@JsonIgnore
	@OneToOne(mappedBy = "cart",fetch = FetchType.LAZY,cascade =CascadeType.ALL)
	private Customer customer;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(
			name = "products_added",
			joinColumns = @JoinColumn(name="cart_id"),
			inverseJoinColumns = @JoinColumn(name="product_id")
			)
	private List<Product> product;
	
	
	public Cart()
	{
		// TODO Auto-generated constructor stub
	}

public Cart(int cartid, int pQuant, Customer customer, List<Product> product)
	{
		this.cartid = cartid;
		this.pQuant = pQuant;
		this.customer = customer;
		this.product = product;
	}

	public int getCartid() {
		return cartid;
	}
	
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	
	public int getpQuant() {
		return pQuant;
	}
	
	public void setpQuant(int pQuant) {
		this.pQuant = pQuant;
	}

/****************************************************************************/	
	
	public Customer getCustomer() 
	{
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> kList) {
		this.product = kList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartid;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + pQuant;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartid != other.cartid)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (pQuant != other.pQuant)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
}
