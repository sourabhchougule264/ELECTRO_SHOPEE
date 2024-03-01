package com.cg.hims.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.Entities.User;
import com.cg.hims.Exceptions.UserNotFoundException;
import com.cg.hims.Service.ISignUpService;

@RestController
@RequestMapping("/SignUp")
public class SignUpController {
	@Autowired
	ISignUpService iSignUpService;

	/*********************************************************************************************************************************************/
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		if (user.getUserid() == null) {
			return new ResponseEntity("Oooops!!!,invalid Id", HttpStatus.NOT_FOUND);
		}
		User user_add = iSignUpService.addUser(user);
		return new ResponseEntity(user_add, HttpStatus.OK);
	}

	/*********************************************************************************************************************************************/
	@DeleteMapping("/removeUser/{id}")
	public ResponseEntity<Void> removeUser(@PathVariable("id") User user) throws UserNotFoundException {
		iSignUpService.removeUser(user);
		return null;

	}

}
