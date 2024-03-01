package com.cg.hims.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Category_Detail")
public class Category 
{
	@Id
	@JoinColumn(name = "cat_id")
	private String catid;
	private String categoryName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Product> product;
	
	public Category() {
		
	}

	public Category(String categoryName,List<Product> product) 
	{
		this.categoryName = categoryName;
		this.product = product;
	}

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
/*********************************************************************************/
	
	public List<Product> getProduct() {
		return product;
	}
	
	public void setProduct(List<Product> product) {
		this.product = product;
	}
}
