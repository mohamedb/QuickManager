package com.quickteam.db;

public class User {
	private int id;
	private String username;
	/*
	 * @role: User/Manager
	 */
	private String role;
	private String password;
	private int managerId;
	
	public User() {
		 
	}
	
	public User(String username,String password,String role) {
		 this.username=username;this.password=password;this.role=role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
