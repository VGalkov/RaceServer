package ru.galkov.raceserver.ActivityResponders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.*;
import org.springframework.web.bind.annotation.*;

import ru.galkov.raceserver.Enums;
import ru.galkov.raceserver.RaceServerApplication;
import ru.galkov.raceserver.db_access.UsersDAO;
import ru.galkov.raceserver.db_access.user_track_DAO;
import ru.galkov.raceserver.db_access.model.Users;


@RestController
public class AskServerTime extends AskRoot implements Enums {
	private static final String ASKER = "AskServerTime";
	private SimpleDateFormat formatForDate = RaceServerApplication.formatForDate;
	private final int USER_GEO_TIMER = RaceServerApplication.USER_GEO_TIMER;
	private final int USER_GEO_FREQ = RaceServerApplication.USER_GEO_FREQ;
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") //,	method = RequestMethod.POST) 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		
		Double altitude = 0.0, latitude = 0.0, longtitude = 0.0;	
		try {
				
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				Date dt = new Date();				
				inBoundJSON = new JSONObject(inJSON);
				setSecurityVars(inBoundJSON);	
				latitude = inBoundJSON.getDouble(fieldsJSON.latitude.toString());	
				altitude = inBoundJSON.getDouble(fieldsJSON.altitude.toString());	
				longtitude = inBoundJSON.getDouble(fieldsJSON.longitude.toString());	
				
	            
				if (serverKey.chkFileKey(clientKey))	{ 
					outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
					outBoundJSON.put(fieldsJSON.date.toString(), formatForDate.format(dt));
					

					final Random random = new Random();
					if (random.nextInt(USER_GEO_TIMER)<=USER_GEO_TIMER/USER_GEO_FREQ) {
						if ((exec_login!=null) && (!exec_login.equals("nobody")) && (!exec_login.equals(""))) {
							UsersDAO uDAO = new UsersDAO();
							Users u = uDAO.getRowIdByLogin(exec_login);
							u.setAltitude(altitude);
							u.setLatitude(latitude);
							u.setLongtitude(longtitude);
							
							uDAO = new UsersDAO();
							uDAO.updateRow(u);
							
							user_track_DAO utDAO = new user_track_DAO();
							utDAO.addRow(u);
						}
					}
					
				}
				else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
		} 
		catch (JSONException e) { workWithError(e, ASKER); }

		return outBoundJSON.toString();
	}

}