package ru.galkov.raceserver.db_access;

import java.sql.*;
import java.text.*;
import java.util.*;
import ru.galkov.raceserver.*;
import ru.galkov.raceserver.db_access.interfaces.activity_log_interface;
import ru.galkov.raceserver.db_access.model.ActivityLog;

public class ActivityLogDAO implements activity_log_interface  {

	private MysqlConnector mc;
	private SimpleDateFormat formatForDate = RaceServerApplication.formatForDate;
	private java.sql.Statement stmt;
	private Connection con;
	
	public ActivityLogDAO() {
		mc = new MysqlConnector();
		con = mc.getConnection();
	}
	
	@Override
	public void close() {
		
		if (stmt!=null) { try {	stmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
		if (mc!=null) { mc.close(); } 
	}
	

	@Override
	public void addRow(ActivityLog log) {
		update("insert into activitylog (`asker`,`json_in`, `json_out`,`login`,`level`) values "
					+ "('" + log.getAsker() + "','" + log.getJson_in() + "','" + log.getJson_out() 
					+ "','" + log.getLogin() + "','" + log.getLevel() + "')");
	}


	@Override
	public List<ActivityLog> getAllRows() {
		List<ActivityLog> al =null;
		try {
			ResultSet res = execute("select * from activitylog");
			al = new ArrayList<ActivityLog>();
			
			while (res.next()) {
				ActivityLog l = new ActivityLog();
				l.setId(res.getLong("id"));
				l.setDt(formatForDate.parse(res.getString("timestamp") + " " + res.getString("timestamp")));
				l.setLogin(res.getString("login"));
				l.setLevel(res.getString("level"));
				l.setAsker(res.getString("asker"));
				l.setJson_in(res.getString("json_in"));
				l.setJson_out(res.getString("json_out"));
				
				al.add(l);
			}
		}
		catch (SQLException | ParseException e) {	logger.error(e.getMessage());	}
		close();	
		return al;
	}


	@Override
	public ActivityLog getRowById(Long id) {
		ActivityLog l = new ActivityLog();
		try {
			ResultSet res = execute("select * from activitylog where id = '"+id+"'");

			l.setId(res.getLong("id"));
			l.setDt(formatForDate.parse(res.getString("timestamp") + " " + res.getString("timestamp")));
			l.setLogin(res.getString("login"));
			l.setLevel(res.getString("level"));
			l.setAsker(res.getString("asker"));
			l.setJson_in(res.getString("json_in"));
			l.setJson_out(res.getString("json_out"));
		} 
		catch (SQLException | ParseException e) {	logger.error(e.getMessage());	}
		close();
		return l;
	}

	
	@Override
	public int count() {
		int num = 0;
		try {
			ResultSet res = execute("select count(*) as num from activitylog");
			num = res.getInt("num");
		} 
		catch (SQLException e) {	logger.error(e.getMessage());	}
		close();
		return num;
	}
	
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
		logger.debug(sql);
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { logger.info(e.getMessage());}
		close();
	}
}