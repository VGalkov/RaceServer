package ru.galkov.raceserver.db_access;

import java.sql.*;
import java.util.*;
import ru.galkov.raceserver.*;
import ru.galkov.raceserver.db_access.interfaces.users_interface;
import ru.galkov.raceserver.db_access.model.Users;


public class UsersDAO implements users_interface {
	
	private MysqlConnector mc;
	private java.sql.Statement stmt;
	private Connection con;
	
	
	public UsersDAO() {
		mc = new MysqlConnector();
		con = mc.getConnection();
	}

	@Override
	public void close() {
		
		if (stmt!=null) { try {	stmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
		if (mc!=null) { mc.close(); } 
	}
	
	
	
	@Override
	public void addRow(Users user) {
		update("insert into users set "
				+ "`login` = '" + user.getLogin() + "',"
				+ "`password` = '" + user.getPassword() + "'");
	}

	@Override
	public void updateRow(Users user) {
		if (user.getId()!=null) {
			update("update users set "
				+ "`login` = '" + user.getLogin() + "',"
				+ "`password` = '" + user.getPassword() + "', "
				+ "`latitude` = '" + user.getLatitude() + "', "
				+ "`altitude` = '" + user.getAltitude() + "', "
				+ "`longtitude` = '" + user.getLongtitude() + "', "
				+ "`master_mark_label`='"+ user.getMaster_mark_label() +"', "
				+ "`registred_race_id`='"+ user.getRegistred_race_id() +"', "
				+ "`registred_start_id`='"+ user.getRegistred_start_id() +"', "
				+ "`level` = '"+ user.getLevel() +"' where  `id` = '" + user.getId()+"'");
		}
		else addRow(user);
	}
	/*
	 * 					u.setRegistred_race_id(res.getLong("registred_race_id"));
					u.setRegistred_start_id(res.getLong("registred_start_id"));
					*/
	
	@Override
	public void deleteRow(Users user)  {
		update("delete from users where `login` = '" + user.getLogin() + "'");
	}

	@Override
	public Users getRowById(Long id)  {
		Users u = null; 
		try {
			ResultSet res = execute("select * from users where `id`="+id);
			if (res.next()) {
				u = new Users();
				u.setId(res.getLong("id"));
				u.setLevel(res.getString("level"));
				u.setLogin(res.getString("login"));
				u.setPassword(res.getString("password"));
				u.setAltitude(res.getDouble("altitude"));
				u.setLatitude(res.getDouble("latitude"));
				u.setLongtitude(res.getDouble("longtitude"));
				u.setMaster_mark_label(res.getString("master_mark_label"));
				u.setRegistred_race_id(res.getLong("registred_race_id"));
				u.setRegistred_start_id(res.getLong("registred_start_id"));
			}

		} catch (SQLException e) { logger.info(e.getMessage());}
		close();
		return u;
	}

	@Override
	public List<Users> getAllRows()  {
		List<Users> users = null;
		try {
				ResultSet res = execute("select * from users");
				users = new ArrayList<Users>();
				while (res.next()) {
					Users u = new Users();
					u.setId(res.getLong("id"));
					u.setLogin(res.getString("login"));
					u.setLevel(res.getString("level"));
					u.setPassword(res.getString("password"));
					u.setAltitude(res.getDouble("altitude"));
					u.setLatitude(res.getDouble("latitude"));
					u.setLongtitude(res.getDouble("longtitude"));
					u.setRegistred_race_id(res.getLong("registred_race_id"));
					u.setRegistred_start_id(res.getLong("registred_start_id"));
					u.setMaster_mark_label(res.getString("master_mark_label"));
					users.add(u);
				}

		} catch (SQLException e) { logger.info(e.getMessage());}
		close();
		return users;
	}
	

	public Users getRowIdByLogin(String login) {
		Users u = null;
		try {
			ResultSet res = execute("select * from users where `login`='" + login + "'");
			if (res.next()) {
				u = new Users();
				u.setId(res.getLong("id"));
				u.setLevel(res.getString("level"));
				u.setLogin(res.getString("login"));
				u.setPassword(res.getString("password"));
				u.setMaster_mark_label(res.getString("master_mark_label"));
				u.setAltitude(res.getDouble("altitude"));
				u.setLatitude(res.getDouble("latitude"));
				u.setLongtitude(res.getDouble("longtitude"));
				u.setRegistred_race_id(res.getLong("registred_race_id"));
				u.setRegistred_start_id(res.getLong("registred_start_id"));
			}

		} catch (SQLException e) { logger.info(e.getMessage());}
		close();
		return u;
	}

	
// ==============================================
	
    
	@Override
	public ResultSet execute(String sql ) {
		ResultSet res = null;
		try {
			stmt = con.createStatement();
			logger.debug(sql);
			res = stmt.executeQuery(sql);
		} 
		catch (SQLException e) {	logger.error(e.getMessage());	}
		return res;
	}
	
	@Override	
	public void update(String sql ) {
		try {
			stmt = con.createStatement();
			logger.debug(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) { logger.info(e.getMessage());}
		close();
	}

	@Override
	public int count() {
		int num = 0;
		try {
			ResultSet res = execute("select count(*) as num from users");
			num = res.getInt("num");
		} 
		catch (SQLException e) {	logger.error(e.getMessage());	}
		close();
		return num;
	}
}
