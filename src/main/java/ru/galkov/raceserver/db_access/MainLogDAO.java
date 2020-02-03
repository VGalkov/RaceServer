package ru.galkov.raceserver.db_access;

import java.sql.*;
import java.text.*;
import java.util.*;
import ru.galkov.raceserver.db_access.interfaces.main_log_interface;
import ru.galkov.raceserver.db_access.model.*;


public class MainLogDAO implements main_log_interface {

	private MysqlConnector mc; 
	private java.sql.Statement stmt;
	private Connection con;
	
	public MainLogDAO() {
		mc = new MysqlConnector();
		con = mc.getConnection();
	}
	
	@Override
	public void close() {
		
		if (stmt!=null) { try {	stmt.close(); } catch (SQLException e) { workWithError(e, e.getMessage()); }}
		if (mc!=null) { mc.close(); } 
	}
	
	
	@Override
	public void addRow(MainLog log) {
		update("insert into main_log (user_id,race_id, mark_id, start_id, datetime, "
					+ "longtitude, latitude, altitude,login,mark_label,master_mark_label,master_mark_delta, "
					+ "mark_master_altitude, mark_master_latitude, mark_master_longtitude)  "
					+ "values ('" + log.getUser_id() + "', '" + log.getRace_id() + "',"
					+ " '" + log.getMark_id() + "', '" + log.getStart_id() + "', NOW(),"
					+ log.getLongtitude() + ", " + log.getLatitude() + ", " + log.getAltitude() 
					+ ", '" + log.getLogin() +"', '" + log.getMark_label() + "','"+log.getMasterMarkLabel()+"',"+log.getMasterMarkLabelDelta()+","
					+ " '" + log.getMark_master_altitude() + "', '" + log.getMark_master_latitude() + "', '" + log.getMark_master_longtitude() + "')");		
	}

	@Override
	public void updateRow(MainLog log)  {
		update("update main_log (user_id,race_id, mark_id, start_id, datetime, "
					+ "longtitude, latitude, altitude,login,mark_label,master_mark_label,master_mark_delta, "
					+ "mark_master_altitude, mark_master_latitude, mark_master_longtitude)  "
					+ "values ('" + log.getUser_id() + "', '" + log.getRace_id() + "',"
					+ " '" + log.getMark_id() + "', '" + log.getStart_id() + "', NOW(),"
					+ log.getLongtitude() + ", " + log.getLatitude() + ", " + log.getAltitude() 
					+ ", '" + log.getLogin() +"', '" + log.getMark_label() + "','"+log.getMasterMarkLabel()+"',"+log.getMasterMarkLabelDelta()
					+ ", '" + log.getMark_master_altitude() + "', '" + log.getMark_master_latitude() + "', '" + log.getMark_master_longtitude() + "')"
					+ "where `id` = '"+log.getId()+"'");
	}

	@Override
	public MainLog getRowById(Long id) {
		MainLog log = null; 
		try {
			ResultSet res = execute("select * from main_log where `id`="+id);
			if (res.next()) {
				log = new MainLog();
				log.setDt(formatForDate.parse(res.getString("datetime") + " " + res.getString("datetime")));
				log.setAltitude(res.getDouble("altitud"));
				log.setLatitude(res.getDouble("latitude"));
				log.setLongtitude(res.getDouble("longtitude"));
				log.setId(res.getLong("id"));
				log.setMark_id(res.getLong("mark_id"));
				log.setRace_id(res.getLong("race_id"));
				log.setStart_id(res.getLong("start_id"));
				log.setUser_id(res.getLong("user_id"));
				log.setLogin(res.getString("login"));
				log.setMark_label(res.getString("mark_label"));
				log.setMasterMarkLabel(res.getString("master_mark_label"));
				log.setMasterMarkDelta(res.getInt("master_mark_delta"));
				log.setMark_master_altitude(res.getDouble("mark_master_altitude"));
				log.setMark_master_latitude(res.getDouble("mark_master_latitude"));
				log.setMark_master_longtitude(res.getDouble("mark_master_longtitude"));
			}

		} catch (SQLException | ParseException e) { workWithError(e, e.getMessage()); }
		finally { close(); }
		return log;
	}

	@Override
	public List<MainLog> getAllRows()  {
		List<MainLog> ml =null;
		try {
			ResultSet res = execute("select * from main_log");
			ml = new ArrayList<MainLog>();
			
			while (res.next()) {
				MainLog m = new MainLog();
				m.setId(res.getLong("id"));
				m.setDt(formatForDate.parse(res.getString("datetime") + " " + res.getString("datetime")));
				m.setLogin(res.getString("login"));
				m.setMark_id(res.getLong("mark_id"));
				m.setRace_id(res.getLong("race_id"));
				m.setUser_id(res.getLong("user_id"));
				m.setStart_id(res.getLong("start_id"));
				m.setAltitude(res.getDouble("altitude"));
				m.setLatitude(res.getDouble("latitude"));
				m.setLongtitude(res.getDouble("longtitude"));			
				m.setMark_label(res.getString("mark_label"));
				m.setMasterMarkLabel(res.getString("master_mark_label"));
				m.setMasterMarkDelta(res.getInt("master_mark_delta"));
				m.setMark_master_altitude(res.getDouble("mark_master_altitude"));
				m.setMark_master_latitude(res.getDouble("mark_master_latitude"));
				m.setMark_master_longtitude(res.getDouble("mark_master_longtitude"));
				
				ml.add(m);
			}
		}
		catch (SQLException | ParseException e) { workWithError(e, e.getMessage());	}
		finally { close(); 	}	
		return ml;
	}

	@Override
	public void deleteRows(MainLog log)  {
		update("delete from main_log where "
					+ "(`user_id` = '"+log.getUser_id()+"') and "
					+ "(`race_id` = '"+log.getRace_id()+"') and"
					+ "(`mark_id` = '"+log.getMark_id()+"') and"
					+ "(`start_id` = '"+log.getStart_id()+"') and"
					+ "(`altitude` = '"+log.getAltitude()+"') and"
					+ "(`latitude` = '"+log.getLatitude()+"') and"
					+ "(`longtitude` = '"+log.getLongtitude()+"')");		
	}
	
	
	public List<MainLog> getRecordsOfCurrentStart(Users user1, long race_id, long start_id) {
		List<MainLog> ml =null;
		try {
			ResultSet res = execute("select * from main_log where "
					+ "(`login`='" + user1.getLogin() + "') and "
					+ "(`start_id` = '" + start_id + "') and"
					+ "(`race_id` = '" + race_id + "')");
			ml = new ArrayList<MainLog>();
			
			while (res.next()) {
				MainLog m = new MainLog();
				m.setId(res.getLong("id"));
				m.setDt(formatForDate.parse(res.getString("datetime") + " " + res.getString("datetime")));
				m.setLogin(res.getString("login"));
				m.setMark_id(res.getLong("mark_id"));
				m.setRace_id(res.getLong("race_id"));
				m.setUser_id(res.getLong("user_id"));
				m.setStart_id(res.getLong("start_id"));
				m.setAltitude(res.getDouble("altitude"));
				m.setLatitude(res.getDouble("latitude"));
				m.setLongtitude(res.getDouble("longtitude"));			
				m.setMark_label(res.getString("mark_label"));
				m.setMasterMarkLabel(res.getString("master_mark_label"));
				m.setMasterMarkDelta(res.getInt("master_mark_delta"));
				m.setMark_master_altitude(res.getDouble("mark_master_altitude"));
				m.setMark_master_latitude(res.getDouble("mark_master_latitude"));
				m.setMark_master_longtitude(res.getDouble("mark_master_longtitude"));
				
				ml.add(m);
			}
		}
		catch (SQLException | ParseException e) {	workWithError(e, e.getMessage());	}
		finally { close(); 	}	
		return ml;
	}
	
	
	
	
	@Override
	public ResultSet execute(String sql ) {
		ResultSet res = null;
		try {
			stmt = con.createStatement();
			logger.debug(sql);
			res = stmt.executeQuery(sql);
		} 
		catch (SQLException e) {	workWithError(e, e.getMessage());	}
		return res;
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
	
	
	
/*
	public boolean checkPassed(long MarkID1, long start_id2, long race_id3, String exec_login4) {
		boolean flag = false;
		ResultSet res = execute("select count(*) as num from main_log where "
				+ "(`user_id` = '" + exec_login4 + "') and" 
				+ "(`race_id` = '" + race_id3 + "') and "
				+ "(`start_id` = '" + start_id2 + "') and"
				+ "(`mark_id` = '" + MarkID1 + "')");
		
		try { if (res.next()) {	 if (res.getLong("num")>0) { flag = true; } }	} 
		catch (SQLException e) {	logger.error(e.getMessage());	flag = false; }
	
		
		return flag;
	}*/
	
	
	@Override
	public int count() {
		int num = 0;
		try {
			ResultSet res = execute("select count(*) as num from main_log");
			num = res.getInt("num");
		} 
		catch (SQLException e) {	logger.error(e.getMessage());	}
		close();
		return num;
	}

}
