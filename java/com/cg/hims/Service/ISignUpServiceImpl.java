package com.cg.hims.Service;

/* AUTHER= SOURABH APPASAHEB CHOUGULE
 * DATE=27/11/2021
 * DESCRIPTION= SIGN UP SERVICE LAYER
 */
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.Entities.User;
import com.cg.hims.Exceptions.UserNotFoundException;
import com.cg.hims.Repository.ILoginRepository;

@Service
@Transactional
public class ISignUpServiceImpl implements ISignUpService {
	@Autowired
	ILoginRepository log_repo;

	/***********************************************************************************************************/
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		User add_user = new User();
		add_user.setUserid(user.getUserid());
		add_user.setPassword(user.getPassword());
		add_user.setRole(user.getRole());
		add_user.setCustomer(user.getCustomer());
		add_user = log_repo.save(user);
		return add_user;
	}

	/***********************************************************************************************************/
	@Override
	public void removeUser(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if (!log_repo.existsById(user.getUserid())) {
			throw new UserNotFoundException("unable to remove,enter valid id");
		}
		log_repo.deleteById(user.getUserid());

	}
}
/***********************************************************************************************************/