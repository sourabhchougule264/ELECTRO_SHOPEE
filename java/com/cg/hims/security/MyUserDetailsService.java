package com.cg.hims.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.hims.Entities.User;
import com.cg.hims.Repository.ILoginRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private ILoginRepository log_repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = log_repo.findById(username);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		return user.map(MyUserDeatils :: new).get();
	}

}
