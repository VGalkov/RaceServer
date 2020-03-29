package ru.galkov.raceserver.db_access;

import java.sql.*;
import java.util.*;
import ru.galkov.raceserver.db_access.interfaces.race_interface;
import ru.galkov.raceserver.db_access.model.Race;


public class RaceDAO implements race_interface {

	private MysqlConnector mc;
	private java.sql.Statement stmt;
	private Connection con;
	
	public RaceDAO() {
		MysqlConnector mc = MysqlConnector.getInstance();
		con = mc.getConnection();
	}
	
	@Override
	public void close() {
		if (stmt!=null) { try {	stmt.close(); } catch (SQLException e) { workWithError(e, e.getMessage()); }}
		if (mc!=null) { mc.close(); } 
	}
	

	@Override
	public void addRow(Race race)  {
		update("insert into race set `name` = '" + race.getLabel() + "'");
		close();
	}

	@Override
	public void updateRow(Race race)  {
		update("update race set `name` = '" + race.getLabel() + "' "
					+ "where `id` ='"+race.getId()+"'");
		close();
	}

	@Override
	public Race getRowById(Long id)  {
		Race r = null;
		try {
			ResultSet res = execute("select * from race where `id`="+id);
			if (res.next()) {
				r = new Race();
				r.setId(res.getLong("id"));
				r.setLabel(res.getString("label"));
				r.setActive(res.getString("active"));
			}

		} 
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();
		return r;
	}
	
	
	@Override
	public Race getActiveRace()  {
		Race r = null;
		try {
			ResultSet res = execute("select * from race where `active`='Y'");
			if (res.next()) {
				r = new Race();
				r.setId(res.getLong("id"));
				r.setLabel(res.getString("label"));
				r.setActive(res.getString("active"));
			}

		} 
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();
		return r;
	}
	
	@Override
	public List<Race> getAllRows()  {
		List<Race> race = new ArrayList<Race>();
		try {
			ResultSet res = execute("select * from race");
			while (res.next()) {
				Race r = new Race();
				r.setId(res.getLong("id"));
				r.setLabel(res.getString("label"));
				r.setActive(res.getString("active"));
				race.add(r);
			}
		}
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();	
		return race;
	}

	@Override
	public void deleteRow(Race race)  {
		update("delete from race where `name` = '" + race.getLabel() + "'");
		close();
	}

	
	@Override
	public void setActive(Long id) {
		update("update race set `active` ='N' where `active` = 'Y'");
		update("update race set `active` ='Y' where id=" + id);
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
	public void update(String sql ) {
		logger.debug(sql);
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { workWithError(e, e.getMessage()); }
	}

	@Override
	public int count() {
		int num = 0;
		try {
			ResultSet res = execute("select count(*) as num from race");
			num = res.getInt("num");
		} 
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();
		return num;
	}	
}
