package dao;

import java.sql.SQLException;

import model.Employee;

public interface Dao {
	void connect() throws SQLException;
    Employee getEmployee(int employeeId, String password) throws SQLException;
	void disconnect() throws SQLException;
}
