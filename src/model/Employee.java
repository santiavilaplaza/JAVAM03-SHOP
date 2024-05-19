package model;

import main.Logable;

public class Employee extends Person implements Logable{
	
	private int employeeId;
	private int user;
	private String password;
	public static final int USER = 123;
	public static final String PASSWORD = "test";	
	
	public Employee( int employeeId, int user, String password) {
		super("test");
		this.employeeId = employeeId;
		this.user = user;
		this.password = password;
	}
	
	public void setEmployeeId(int employeeID) {
		this.employeeId = employeeID;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setUser(int user) {
		this.user = user;
	}
	
	public int getUser() {
		return user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean login(int user, String password) {
		if (user == USER && password.equals(PASSWORD)) {
				return true;
		} else {
			return false;
		}
	}
}