package com.cg.hims.authentication.entity;

public class AuthenticationRequestDTO {

	private String userName;
	private String password;

	public AuthenticationRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticationRequestDTO(String userName, String password) {

		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
