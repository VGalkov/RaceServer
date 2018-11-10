package ru.galkov.raceserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class MysqlConnector  { // implements Connection?
	
	private static String connectionUrl = RaceServerApplication.connectionUrl;
	private static String connectionUser = RaceServerApplication.connectionUser;
	private static String connectionPassword = RaceServerApplication.connectionPassword;
//	private static Logger logger = RaceServerApplication.logger; 

	private Connection con;
	
		
	public MysqlConnector() {
		setConnect();
	}
	
	public Connection getConnection() {
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

	public void close() {
		if (con!=null) { try {	con.close(); } catch (SQLException e) { e.printStackTrace(); }	}
	}
}