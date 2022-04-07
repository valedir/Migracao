package database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	
	public Connection connection(String url, String user, String password) throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public Statement getStatement(Connection conn) throws SQLException {
		return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	}
	
	public ResultSet getResultset(Statement stmt, String query) throws SQLException {
		return stmt.executeQuery(query);
		
	}
	
	public void displayData(ResultSet rs) throws SQLException {
		while (rs.next()) {
			int id = rs.getInt(1);
			double temp = rs.getDouble(2);
			double humid = rs.getDouble(3);
			double luz = rs.getDouble(4);
			System.out.println("id: " + id + " temp: " + temp + " humid: " + humid + " luz: " + luz);
		}
	}
}
