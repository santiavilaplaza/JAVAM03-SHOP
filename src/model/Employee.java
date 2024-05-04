package model;

import main.Logable;

public class Employee extends Person implements Logable{
	
	private int employeeId;
	private int USER;
	private String PASSWORD;
	
	public Employee( int employeeId, int USER, String PASSWORD) {
		super("test");
		this.employeeId = employeeId;
		this.USER = USER;
		this.PASSWORD = PASSWORD;
	}
	
	public void setEmployeeId(int employeeID) {
		this.employeeId = employeeID;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setUSER(int USER) {
		this.USER = USER;
	}
	
	public int getUSER() {
		return USER;
	}
	
	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}
	
	public String getPASSWORD() {
		return PASSWORD;
	}
	
	public boolean login(int USER, String PASSWORD) {
		if (USER == 123 && PASSWORD.equals("test")) {
				return true;
		} else {
			return false;
		}
	}
}