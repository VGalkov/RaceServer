package ru.galkov.raceserver.db_access;

import java.sql.*;
import java.util.*;
import ru.galkov.raceserver.db_access.interfaces.nfc_marks_interface;
import ru.galkov.raceserver.db_access.model.NFCmarks;

public class NFCmarksDAO implements nfc_marks_interface {

	
	private MysqlConnector mc;
	private java.sql.Statement stmt;
	private Connection con;
	
	public NFCmarksDAO() {
		mc = new MysqlConnector();
		con = mc.getConnection();
	}
	
	public void close() {
		
		if (stmt!=null) { try {	stmt.close(); } catch (SQLException e) { workWithError(e, e.getMessage()); }}
		if (mc!=null) { mc.close(); } 
	}
	
	@Override
	public void addRow(NFCmarks mark) {
		update("insert into nfc_marks (`label`, `race_id`, `altitude`, `latitude`, `longtitude`, `counter`) values"
				+ "('"+mark.getLabel()+"', '"+mark.getRace_id()+"', "+mark.getAltitude()+", "+mark.getLatitude()
				+", "+mark.getLongtitude()+", '"+ mark.getCounter() +"')");
	}

	@Override
	public void updateRow(NFCmarks mark)  {
		if (mark.getId() != null) {
			update("update nfc_marks set `label` = '"+mark.getLabel()+"', `race_id` = '"+mark.getRace_id()+"', "
				+ "altitude="+mark.getAltitude()+", latitude="+mark.getLatitude()+", "
				+ "longtitude="+mark.getLongtitude()+"' "
				+ "where `id` = '"+mark.getId()+"'");
		}
		else {addRow(mark); }
	}


	@Override
	public NFCmarks getRowById(Long id) {
		NFCmarks n = null;
		try {
				ResultSet res = execute("select * from nfc_marks where `id`="+id);
				if (res.next()) {
					n = new NFCmarks();
					n.setId(res.getLong("id"));
					n.setLabel(res.getString("label"));
					n.setAltitude(res.getDouble("altitude"));
					n.setLatitude(res.getDouble("latitude"));
					n.setLongtitude(res.getDouble("longtitude"));
				}
		} 
		catch (SQLException e) {	workWithError(e, e.getMessage());	}
		close(); 
		return n;
	}



	@Override
	public List<NFCmarks> getAllRows()  {
		List<NFCmarks> marks = null;
		try {
				ResultSet res = execute("select * from nfc_marks");
				marks = new ArrayList<NFCmarks>();
				while (res.next()) {
					NFCmarks m = new NFCmarks();
					m.setLabel(res.getString("label"));
					m.setId(res.getLong("id"));
					m.setAltitude(res.getDouble("altitude"));
					m.setLatitude(res.getDouble("latitude"));
					m.setLongtitude(res.getDouble("longtitude"));
					
					marks.add(m);
				}

		} catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();

		return marks;
	}

	@Override
	public void deleteRow(NFCmarks mark)  {
		update("delete from nfc_marks where `label` = '"	+ mark.getLabel() + "'");
	}
	
	public NFCmarks getMasterMark() {
		NFCmarks n = null;
		try {
				ResultSet res = execute("select * from nfc_marks where `type`='master' limit 1");
				if (res.next()) {
					n = new NFCmarks();
					n.setId(res.getLong("id"));
					n.setLabel(res.getString("label"));
					n.setAltitude(res.getDouble("altitude"));
					n.setLatitude(res.getDouble("latitude"));
					n.setLongtitude(res.getDouble("longtitude"));
				}
		} 
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		close(); 	
		return n;
	}
	
	public NFCmarks getNext(int counter) {
		NFCmarks n = null;
		try {
				 
				ResultSet res = execute("select * from nfc_marks where counter>'"+counter+"' having min(counter)");
				if (res.next()) {
					n = new NFCmarks();
					n.setId(res.getLong("id"));
					n.setLabel(res.getString("label"));
					n.setAltitude(res.getDouble("altitude"));
					n.setLatitude(res.getDouble("latitude"));
					n.setLongtitude(res.getDouble("longtitude"));
					n.setCounter(res.getInt(counter));
				}
		} 
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		close(); 
		return n;
	}
	
	
	public NFCmarks getRowIdByLabel(String mark) {
		NFCmarks n = null;
		try {
				ResultSet res = execute("select * from nfc_marks where `label`='" + mark + "'");
				if (res.next()) {
					n = new NFCmarks();
					n.setId(res.getLong("id"));
					n.setLabel(res.getString("label"));
					n.setAltitude(res.getDouble("altitude"));
					n.setLatitude(res.getDouble("latitude"));
					n.setLongtitude(res.getDouble("longtitude"));
				}
		} 
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		close(); 	
		return n;
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
		close();
	}

	@Override
	public int count() {
		int num = 0;
		try {
			ResultSet res = execute("select count(*) as num from nfc_marks");
			num = res.getInt("num");
		} 
		catch (SQLException e) { workWithError(e, e.getMessage()); }
		close();
		return num;
	}	
}
