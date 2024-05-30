package model;

import java.sql.SQLException;

import dao.Dao;
import dao.DaoImplJDBC;
import main.Logable;

public class Employee extends Person implements Logable{
	
	private int employeeId;
	private String user;
	private String password;
	public static final int USER = 123;
	public static final String PASSWORD = "test";	
	
	public Dao dao;
	
	public Employee(int employeeId, String user, String password) {
		super("test");
		this.employeeId = employeeId;
		this.user = user;
		this.password = password;
		this.dao = new DaoImplJDBC();
	}
	
	public Employee(int employeeId, String password) {
		super("test");
		this.employeeId = employeeId;
		this.password = password;
		this.dao = new DaoImplJDBC();
	}
	
	public void setEmployeeId(int employeeID) {
		this.employeeId = employeeID;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean login(int user, String password) {
//		if (user == USER && password.equals(PASSWORD)) {
//				return true;
//		} else {
//			return false;
//		}
		
		try {
			dao.connect();
			
			Employee employee = dao.getEmployee(user, password);
			
			dao.disconnect();
			
			if (employee != null && employee.getEmployeeId() == employeeId && employee.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}