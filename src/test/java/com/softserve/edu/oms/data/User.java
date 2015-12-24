package com.softserve.edu.oms.data;

interface IFirstname {
	ILastname setFirstname(String firstname);
}

interface ILastname {
	ILogin setLastname(String lastname);
}

interface ILogin {
	IPassword setLogin(String login);
}

interface IPassword {
	IEmail setPassword(String password);
}

interface IEmail {
	IRegion setEmail(String email);
}

interface IRegion {
	IRole setRegion(String region);
}

interface IRole {
	IBuild setRole(String role);
}

interface IBuild {
	IUser build();
}

public class User implements IFirstname, ILastname, ILogin, IPassword, IEmail,
		IRegion, IRole, IBuild, IUser {
	private String firstname;
	private String lastname;
	private String login;
	private String password;
	private String email;
	private String region;
	private String role;

	private User() {
	}

	// public User(String firstname, String lastname, String login,
	// String password, String email, String region, String role) {
	// this.firstname = firstname;
	// this.lastname = lastname;
	// this.login = login;
	// this.password = password;
	// this.email = email;
	// this.region = region;
	// this.role = role;
	// }

	// set - - - - - - - - - -

	public static IFirstname get() {
		return new User();
	}
	
	public ILastname setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public ILogin setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public IPassword setLogin(String login) {
		this.login = login;
		return this;
	}

	public IEmail setPassword(String password) {
		this.password = password;
		return this;
	}

	public IRegion setEmail(String email) {
		this.email = email;
		return this;
	}

	public IRole setRegion(String region) {
		this.region = region;
		return this;
	}

	public IBuild setRole(String role) {
		this.role = role;
		return this;
	}

	public IUser build() {
		return this;
	}

	// get - - - - - - - - - -

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getRegion() {
		return region;
	}

	public String getRole() {
		return role;
	}

}
