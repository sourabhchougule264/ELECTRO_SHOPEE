package com.cg.hims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cg.hims.Entities.User;
import com.cg.hims.Exceptions.InvalidCredentialsException;
import com.cg.hims.authentication.entity.AuthenticationRequestDTO;
import com.cg.hims.authentication.entity.AuthenticationResponseDTO;
import com.cg.hims.security.MyUserDetailsService;

import com.cg.hims.utils.JWTUtils;

@Controller
@RequestMapping("/rest/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	private JWTUtils jwtUtil;

	public AuthController(AuthenticationManager authenticationManager, JWTUtils jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;

	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {

		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequestDTO.getUserName(), authenticationRequestDTO.getPassword()));
			// finaling the user details to avoid the change in the user details in between

			if (authentication.isAuthenticated()) {
				final UserDetails userDetails = userDetailsService
						.loadUserByUsername(authenticationRequestDTO.getUserName());

				// finalizing the jwt token to avoid in between change of token
				final String jwt = jwtUtil.generateToken(userDetails);

				return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
			} else {
				InvalidCredentialsException errorResponse = new InvalidCredentialsException(HttpStatus.UNAUTHORIZED,
						"Authentication Failed");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
			}

		} catch (BadCredentialsException e) {
			InvalidCredentialsException errorResponse = new InvalidCredentialsException(HttpStatus.BAD_REQUEST,
					"Invalid username or password");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			InvalidCredentialsException errorResponse = new InvalidCredentialsException(HttpStatus.BAD_REQUEST,
					"Invalid username or password");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

}
