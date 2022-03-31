package database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	
	private static final String USER = "aluno";
	private static final String PASSWORD = "aluno";
	private static final String URL = "jdbc:mysql://194.210.86.10:3306/sid2022?serverTimezone=UTC";
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public void connect() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connect sucessfully");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void printData() {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM zona");
			while (rs.next()) {
				int id = rs.getInt(1);
				double temp = rs.getDouble(2);
				double humid = rs.getDouble(3);
				double luz = rs.getDouble(4);	
				System.out.println("id: "+id+" temp: "+temp+" humid: "+humid+" luz: "+luz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		MySQL client = new MySQL();
		client.connect();
		client.printData();
	}

}
