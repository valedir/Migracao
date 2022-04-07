package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.mysql.ConnectionString;
import database.mysql.MySQL;

public class MongoToMysql {

	public static void main(String[] args) {
		MySQL mysql = new MySQL();
		ConnectionString conn = new ConnectionString();

		conn.setUrl("jdbc:mysql://194.210.86.10:3306/sid2022?serverTimezone=UTC");
		conn.setUser("aluno");
		conn.setPassword("aluno");

		try (Connection connection = mysql.connection(conn.getUrl(), conn.getUser(), conn.getPassword());
				Statement stmt = mysql.getStatement(connection);
				ResultSet rs = mysql.getResultset(stmt, "SELECT * FROM zona");) {
			mysql.displayData(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
