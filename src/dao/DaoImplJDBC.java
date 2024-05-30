package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;

public class DaoImplJDBC implements Dao {
	
	private Connection conn;
	
	// Método para conectar con la bbdd
	@Override
	public void connect() throws SQLException {
		// Define connection parameters
		String url = "jdbc:mysql://localhost:3306/shop";
		String user = "root";
		String pass = "";
		this.conn = DriverManager.getConnection(url, user, pass);
	}

	@Override
	public Employee getEmployee(int employeeId, String password) throws SQLException {
		Employee employee = null;
		
		String query = "SELECT * FROM employee where employeeId = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, employeeId);
			try (ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
	        		employee =  new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));            		            				
	        	}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return employee;
	}

	@Override
	public void disconnect() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		
	}
	
}
