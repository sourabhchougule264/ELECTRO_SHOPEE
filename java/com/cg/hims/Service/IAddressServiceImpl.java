package com.cg.hims.Service;

/* AUTHER= SOURABH APPASAHEB CHOUGULE
 * DATE=27/11/2021
 * DESCRIPTION= ADDRESS SERVICE LAYER
 */
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.Entities.Address;
import com.cg.hims.Entities.Customer;
import com.cg.hims.Exceptions.AddressNotFoundException;
import com.cg.hims.Repository.IAddressRepository;

@Service
@Transactional
public class IAddressServiceImpl implements IAddressService {
	@Autowired
	IAddressRepository Add_Repo;

	@PersistenceContext
	private EntityManager entityManager;

	/****************************************************************************************************************/
	@Override
	public Address addAddress(Address add) {
		// TODO Auto-generated method stub
		Address address_add = new Address();
		address_add.setAddressid(add.getAddressid());
		address_add.setBuildingName(add.getBuildingName());
		address_add.setCity(add.getCity());
		address_add.setCountry(add.getCountry());
		address_add.setState(add.getState());
		address_add.setStreetNo(add.getStreetNo());
		address_add.setPincode(add.getPincode());
		address_add = Add_Repo.save(add);
		return address_add;
	}

	/****************************************************************************************************************/
	@Override
	public Address updateAddress(int id, Address add) throws AddressNotFoundException {
		// TODO Auto-generated method stub
		Address address_add = Add_Repo.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("Addresss not matched!!!!"));
		address_add.setAddressid(add.getAddressid());
		address_add.setBuildingName(add.getBuildingName());
		address_add.setCity(add.getCity());
		address_add.setCountry(add.getCountry());
		address_add.setState(add.getState());
		address_add.setStreetNo(add.getStreetNo());
		address_add.setPincode(add.getPincode());
		Customer cysCustomer = new Customer();
		cysCustomer.setCustomerid(add.getCustomer().getCustomerid());
		cysCustomer.setFirstName(add.getCustomer().getFirstName());
		cysCustomer.setLastName(add.getCustomer().getLastName());
		cysCustomer.setEmail(add.getCustomer().getEmail());
		cysCustomer.setMobileNumber(add.getCustomer().getMobileNumber());

		address_add.setCustomer(cysCustomer);

//	if (!Add_Repo.existsById(add.getAddressid())){
//		throw new AddressNotFoundException("Oops!!!!,Unable to update,please enter valisd Id");
//	}
		entityManager.merge(address_add);
		return address_add;
	}

	/****************************************************************************************************************/
	@Override
	public int removeAddress(int Aid, Address add) throws AddressNotFoundException {
		// TODO Auto-generated method stub
//		if (!Add_Repo.existsById(add.getAddressid()))
//		{
//		    throw new AddressNotFoundException("Ooopsss!!!, unable to remove, enter valis id");	
//		}
		Add_Repo.removeAddress(Aid);
		return 1;
	}

	/****************************************************************************************************************/
	@Override
	public Address viewAllAddress(int id) {
		// TODO Auto-generated method stub
		Address view_allAddress = Add_Repo.findAllById(id);
		return view_allAddress;
	}

	/****************************************************************************************************************/
	@Override
	public Address viewAddress(int Aid, Address add) {
		// TODO Auto-generated method stub
		Address view_address = Add_Repo.viewAddress(Aid);
		return view_address;
	}
}
