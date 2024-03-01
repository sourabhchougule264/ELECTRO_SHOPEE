package com.cg.hims.authentication.entity;

import java.io.Serializable;

public class AuthenticationResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String jwt;

	public AuthenticationResponseDTO(String jwt) {
		this.jwt = jwt;
	}

	public String getJWT() {
		return jwt;

	}

}
