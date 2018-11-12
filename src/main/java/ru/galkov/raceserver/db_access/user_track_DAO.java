package ru.galkov.raceserver.db_access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import ru.galkov.raceserver.MysqlConnector;
import ru.galkov.raceserver.db_access.interfaces.users_interface;
import ru.galkov.raceserver.db_access.model.Users;

//TODO дописать класс.
public class user_track_DAO implements users_interface {
	
	private MysqlConnector mc;
	private java.sql.Statement stmt;
	private Connection con;
	
	
	public user_track_DAO() {
		mc = new MysqlConnector();
		con = mc.getConnection();
	}


	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ResultSet execute(String sql) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(String sql) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addRow(Users user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateRow(Users user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Users getRowById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Users> getAllRows() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteRow(Users user) {
		// TODO Auto-generated method stub
		
	}
	
}
