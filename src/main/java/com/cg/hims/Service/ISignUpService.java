package com.cg.hims.Service;

import com.cg.hims.Entities.User;
import com.cg.hims.Exceptions.UserNotFoundException;

public interface ISignUpService {

	public User addUser(User user);

	public void removeUser(User user) throws UserNotFoundException;

}
