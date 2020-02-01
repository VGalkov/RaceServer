package ru.galkov.raceserver.ActivityResponders;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication;
import ru.galkov.raceserver.RaceServerApplication.*;
import ru.galkov.raceserver.db_access.*;
import ru.galkov.raceserver.db_access.model.*;

@RestController
public class SendUserNFCDiscovery  extends AskRoot {
	
	private static final String ASKER = "SendUserNFCDiscovery";
	private SimpleDateFormat formatForDate = RaceServerApplication.formatForDate;


	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		String exec_level ="Error", exec_login = "nobody", login = "", mark = "", master_mark_label="", clientKey = "";
		Double longitude = 0.00, altitude = 0.00, latitude = 0.00, mark_master_altitude=0.0,mark_master_longitude=0.0,
				mark_master_latitude=0.0;	
		long race_id = 0L, start_id = 0L, master_mark_delta = 0L;

		try {		
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);

				master_mark_label = inBoundJSON.getString(fieldsJSON.master_mark_label.toString()); 
				master_mark_delta = inBoundJSON.getLong(fieldsJSON.master_mark_delta.toString()); 
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString()); 
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString()); 
				clientKey = inBoundJSON.getString(fieldsJSON.key.toString()); 
				mark = inBoundJSON.getString(fieldsJSON.mark.toString()); 
				start_id = inBoundJSON.getLong(fieldsJSON.start.toString()); 
				race_id = inBoundJSON.getLong(fieldsJSON.race.toString()); 				
				login = inBoundJSON.getString(fieldsJSON.login.toString()); 
				altitude = Double.parseDouble(inBoundJSON.getString(fieldsJSON.altitude.toString())); 
				latitude = Double.parseDouble(inBoundJSON.getString(fieldsJSON.latitude.toString())); 
				longitude = Double.parseDouble(inBoundJSON.getString(fieldsJSON.longitude.toString())); 
				mark_master_longitude = Double.parseDouble(inBoundJSON.getString(fieldsJSON.mark_master_longitude.toString())); 
				mark_master_altitude = Double.parseDouble(inBoundJSON.getString(fieldsJSON.mark_master_altitude.toString())); 
				mark_master_latitude = Double.parseDouble(inBoundJSON.getString(fieldsJSON.mark_master_latitude.toString())); 			
				
		if (serverKey.chkFileKey(clientKey))	{

			Date dt = new Date();
			Long markID = null;
			try { markID = getMarkID(mark); } 
			catch(NullPointerException e) {
				logger.debug("Метке не зарегистрирована в системе!");
			}
			Long userID = getUserId(login);			
				
			MainLogDAO main_log = new MainLogDAO();
			MainLog main_logDB = new MainLog();
			
			main_logDB.setDt(dt);
			main_logDB.setMark_id(markID);
			main_logDB.setUser_id(userID);
			main_logDB.setRace_id(race_id);
			main_logDB.setStart_id(start_id);
			main_logDB.setAltitude(altitude);
			main_logDB.setLatitude(latitude);
			main_logDB.setLongtitude(longitude);
			main_logDB.setLogin(login); 
			main_logDB.setMark_label(mark);
			main_logDB.setMasterMarkLabel(master_mark_label);			
			main_logDB.setMasterMarkDelta(master_mark_delta);
			main_logDB.setMark_master_altitude(mark_master_altitude);
			main_logDB.setMark_master_latitude(mark_master_latitude);
			main_logDB.setMark_master_longtitude(mark_master_longitude);
			main_log.addRow(main_logDB);
			
			outBoundJSON.put(fieldsJSON.status.toString(), trigger.TRUE); // if sucess
			outBoundJSON.put(fieldsJSON.altitude.toString(),altitude);
			outBoundJSON.put(fieldsJSON.latitude.toString(),latitude);
			outBoundJSON.put(fieldsJSON.longitude.toString(),longitude);
			outBoundJSON.put(fieldsJSON.mark.toString(),mark);
			outBoundJSON.put(fieldsJSON.login.toString(),login);
			outBoundJSON.put(fieldsJSON.start_id.toString(),start_id);
			outBoundJSON.put(fieldsJSON.race_id.toString(),race_id);
			outBoundJSON.put(fieldsJSON.date.toString(),formatForDate.format(dt));
			outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
			
		}
		else { outBoundJSON.put(fieldsJSON.error.toString(), fieldsJSON.key + "- не верный!"); }
		
	} 
	catch (JSONException e) { workWithError(e, ASKER); } 	
		
		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
		return outBoundJSON.toString();
	}

 private Long getMarkID(String mark1) {
		NFCmarksDAO marks = new NFCmarksDAO();
		NFCmarks m = marks.getRowIdByLabel(mark1); 
		return  m.getId();
 }
 
 private Long getUserId(String login1) {
	UsersDAO users = new UsersDAO();
	Users u = users.getRowIdByLogin(login1); 			
	return u.getId();
 }
 
	
}
