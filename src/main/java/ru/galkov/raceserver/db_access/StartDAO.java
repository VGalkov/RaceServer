package ru.galkov.raceserver.db_access;

import java.sql.*;
import java.util.*;
import ru.galkov.raceserver.*;
import ru.galkov.raceserver.db_access.interfaces.start_interface;
import ru.galkov.raceserver.db_access.model.Start;

public class StartDAO implements start_interface {

	private MysqlConnector mc; 
	private java.sql.Statement stmt;
	private Connection con;
	
	public StartDAO() {
		mc = new MysqlConnector();
		con = mc.getConnection();
	}
	
	
	@Override
	public void close() {
		
		if (stmt!=null) { try {	stmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
		if (mc!=null) { mc.close(); } 
	}
	
	
	@Override
	public void addRow(Start start){
		update("insert into start (`active`,`label`,`race_id`, `start_time`, `stop_time`) values "
				+ "('"+ start.getActive() +"','"+start.getLabel()+"','"+start.getRace_id()+"',"
				+ " '"+ start.getStart_time() +"','"+start.getStop_time()+"')");
		close();
	}
	
	@Override
	public void setActive(Long id) {
		update("update start set `active` ='N'");
		update("update start set `active` ='Y' where id=" + id);
		close();
	}

	@Override
	public void updateRow(Start start) {	
		update("update start set "
				+ "`active` = '"+ start.getActive() +"',"
				+ "`label`  = '"+start.getLabel()+"',"
				+ "`race_id`= '"+start.getRace_id()+"',"
				+ "`start_time`= '"+start.getStart_time()+"',"
				+ "`stop_time`= '"+start.getStop_time()+"'"
//				+ "`start_time`= '2017-09-01 10:30:00',"
//				+ "`stop_time`= '2019-09-01 12:00:00'"
				+ " where `id` = '"+start.getId()+"'");
		close();
	}

	@Override
	public Start getRowById(Long id)  {
		Start s = null;
		try {
			ResultSet res = execute("select * from start where `id`="+id);
			if (res.next()) {
				s = new Start();
				s.setId(res.getLong("id"));
				s.setLabel(res.getString("label"));
				s.setRace_id(res.getLong("race_id"));
				s.setActive(res.getString("active"));
				s.setStart_time(res.getString("start_time"));
				s.setStop_time(res.getString("stop_time"));
			}
		}
		 catch (SQLException e) { workWithError(e, e.getMessage());}
		close();	
		return s;
	}

	@Override
	public List<Start> getRaceIdRows(long race_id) {
		List<Start> start = null;
		start =  new ArrayList<Start>();
		try {
			ResultSet res = execute("select * from start where race_id='" + race_id + "'");
			while (res.next()) {
			Start s = new Start();
				s.setId(res.getLong("id"));
				s.setLabel(res.getString("label"));
				s.setRace_id(res.getLong("race_id"));
				s.setActive(res.getString("active"));
				s.setStart_time(res.getString("start_time"));
				s.setStop_time(res.getString("stop_time"));
				start.add(s);
			}
		}
		 catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();
		return start;
	}
	
	
	@Override
	public List<Start> getAllRows() {
		List<Start> start = null;
		start =  new ArrayList<Start>();
		try {
			ResultSet res = execute("select * from start");
			while (res.next()) {
				Start s = new Start();
				s.setId(res.getLong("id"));
				s.setLabel(res.getString("label"));
				s.setRace_id(res.getLong("race_id"));
				s.setActive(res.getString("active"));
				s.setStart_time(res.getString("start_time"));
				s.setStop_time(res.getString("stop_time"));
				start.add(s);
			}
		}
		 catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();
		return start;
	}

	@Override
	public void deleteRow(Start start){
		update("DELETE FROM start WHERE "
					+ "(`label` = '"+start.getLabel()+"') and "
					+ "(`race_id` = '"+start.getRace_id()+"')");	
		close();
	}

	@Override
	public Start getActiveStart()  {
		Start s = null;
		try {
		ResultSet res = execute("select * from start where `active`='Y'");
		if (res.next()) {
			s = new Start();
			s.setId(res.getLong("id"));
			s.setLabel(res.getString("label"));
			s.setRace_id(res.getLong("race_id"));
			s.setActive(res.getString("active"));
			s.setStart_time(res.getString("start_time"));
			s.setStop_time(res.getString("stop_time"));
		}
		}
		 catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();
		return s;
	}
	
	// =============================
	
	@Override	
	public void update(String sql ) {
		logger.debug(sql);
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { workWithError(e, e.getMessage()); }
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
	public int count() {
		int num = 0;
		try {
			ResultSet res = execute("select count(*) as num from start");
			num = res.getInt("num");
		} 
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();
		return num;
	}
	
}
