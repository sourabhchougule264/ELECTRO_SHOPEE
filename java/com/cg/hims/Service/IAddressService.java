package com.cg.hims.Service;


import com.cg.hims.Entities.Address;
import com.cg.hims.Exceptions.AddressNotFoundException;

public interface IAddressService {
	
	public Address addAddress(Address add);
	public Address updateAddress(int id,Address add) throws AddressNotFoundException;
	public int removeAddress(int Aid,Address add) throws AddressNotFoundException;
	public Address viewAllAddress(int id);
	public Address viewAddress(int Aid,Address add) throws AddressNotFoundException;

}
