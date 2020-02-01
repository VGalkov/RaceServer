package ru.galkov.raceserver.db_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ru.galkov.raceserver.RaceServerApplication;



class MysqlConnector  { 
	
	private static String connectionUrl = RaceServerApplication.connectionUrl;
	private static String connectionUser = RaceServerApplication.connectionUser;
	private static String connectionPassword = RaceServerApplication.connectionPassword; 

	protected Connection con;
	
		
	MysqlConnector() {
		setConnect();
	}
	
	Connection getConnection() {
		return con;
	}
	
	
	private void setConnect() {
	
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
           	con = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		} 
		catch (SQLException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
	}

	void close() {
		if (con!=null) { try {	con.close(); } catch (SQLException e) { e.printStackTrace(); }	}
	}
}