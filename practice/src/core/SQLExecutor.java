package core;

import java.sql.*;

public class SQLExecutor {
	private Connection connection;
	public void Connect() throws SQLException{
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oldpopsong?serverTimezone=Asia/Seoul", "user", "1234");
	}
	public void ConnectRoot() throws SQLException{
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=Asia/Seoul&allowLoadLocalInfile=true","root","1234");
	}
	public ResultSet ExecuteReadQuery(String sql) throws SQLException{
		Statement stmt = connection.createStatement();
		return stmt.executeQuery(sql);
	}
	public void ExecuteUpdateQuery(String sql) throws SQLException{
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(sql);
	}
	public void ExecuteSettingQuery(String sql) throws SQLException{
		Statement stmt = connection.createStatement();
		stmt.execute(sql);
	}
	public void Close() throws SQLException{
		connection.close();
	}
}
	