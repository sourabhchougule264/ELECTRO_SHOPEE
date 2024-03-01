package com.cg.hims.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name ="User_Detail")
public class User {
	
	@Id
	@NotNull
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z0-9.@]{10,20}",message = "Id must include Lower case lettes,Upper case letters,.@,Numbers")
	@Column(name = "user_id")
	private String userid;
	
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z0-9@]{10,20}",message = "Password must include Lower case lettes,Upper case letters,@,Numbers")
	private String password;
	
	@NotEmpty(message = "Role should not be blank")
	@Size(min = 2)
	private String role;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Customer customer;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String password, String role,Customer customer) 
	{
	
		this.password = password;
		this.role = role;
		this.customer = customer;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}




}
