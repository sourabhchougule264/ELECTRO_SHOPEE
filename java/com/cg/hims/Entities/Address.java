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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "address_Detail")
public class Address {
 
	 @Id
	 @NotNull
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "address_id")
	 private int addressid;
	 private String streetNo;
	 private String buildingName;
	 private String city;
	 private String state;
	 private String country;
	 private int pincode;
	 
	 @JsonIgnore
	 @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	 @JoinColumn(name = "customer_id")
	 private Customer customer;
     
	 @JsonIgnore
	 @OneToMany(mappedBy = "address",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	 private List<Order> order;
	 
	 public Address() {
		
	    }
	
	 public Address( String streetNo, String buildingName, String city, String state, String country,
			int pincode, Customer customer, List<Order> order) {
		this.streetNo = streetNo;
		this.buildingName = buildingName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.customer = customer;
		this.order = order;
	    }

	 	public int getAddressid() {
			return addressid;
		}

		public void setAddressid(int adressid) {
			this.addressid = adressid;
		}

		public String getStreetNo() {
			return streetNo;
		}

		public void setStreetNo(String streetNo) {
			this.streetNo = streetNo;
		}

		public String getBuildingName() {
			return buildingName;
		}

		public void setBuildingName(String buildingName) {
			this.buildingName = buildingName;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public int getPincode() {
			return pincode;
		}

		public void setPincode(int pincode) {
			this.pincode = pincode;
		}
/***************************************************************************************************/
		
		public Customer getCustomer() {
				return customer;
		}

		public void setCustomer(Customer customer) {
				this.customer = customer;
		}
	
		public List<Order> getOrder() {
				return order;
		}

		public void setOrder(List<Order> order) {
				this.order = order;
		}
}
