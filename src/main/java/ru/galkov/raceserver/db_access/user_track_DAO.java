package ru.galkov.raceserver.db_access;

import java.sql.*;
import java.util.List;
import ru.galkov.raceserver.db_access.interfaces.users_interface;
import ru.galkov.raceserver.db_access.model.Users;

//TODO дописать класс.
public class user_track_DAO implements users_interface {
	
	private MysqlConnector mc;
	private java.sql.Statement stmt;
	private Connection con;
	
	
	public user_track_DAO() {
		MysqlConnector mc = MysqlConnector.getInstance();
		con = mc.getConnection();
	}


	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void close() {
		if (stmt!=null) { try {	stmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
		if (mc!=null) { mc.close(); } 
		
	}


	@Override	
	public void update(String sql ) {
		logger.debug(sql);
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();
	}
	
	@Override
	public ResultSet execute(String sql ) {
		ResultSet res = null;
		try {
			stmt = con.createStatement();
			logger.debug(sql);
			res = stmt.executeQuery(sql);
		} 
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		return res;
	}

	@Override
	public void addRow(Users user) {
		update("insert into user_track set "
				+ "`user_id` = '" + user.getId() + "',"
				+ "`datetime` = NOW(),"
				+ "`latitude`= "+user.getLatitude()+","
				+ "`altitude`= "+user.getAltitude()+","
				+ "`longtitude` ="+user.getLongtitude());
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
