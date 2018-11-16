package ru.galkov.raceserver.ActivityResponders;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.RaceServerApplication.access;
import ru.galkov.raceserver.db_access.StartDAO;
import ru.galkov.raceserver.db_access.UsersDAO;
import ru.galkov.raceserver.db_access.model.Start;
import ru.galkov.raceserver.db_access.model.Users;

@RestController
public class AskRaceConfig  extends AskRoot {
	private static final String ASKER = "AskRaceConfig";	
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		String exec_level ="Error", exec_login = "nobody", clientKey = "";	
		JSONObject outBoundJSON = new JSONObject();
		JSONObject inBoundJSON = null;
		try {

		/*	
			{
				"race_id":X, 
				"start_id":Y,
				"start_datetime":"",
				"stop_datetime":"",
				"users":	[
							login, 
							master_mark,
							start_id,
							race_id
							latitude,
							altitude,
							longitude 
						]
			}
		*/
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);

				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
				
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());

		            StartDAO sDAO = new StartDAO();
					Start start =  sDAO.getActiveStart();
					
					outBoundJSON.put(fieldsJSON.race_id.toString(), start.getRace_id());
					outBoundJSON.put(fieldsJSON.start_id.toString(), start.getId());
					outBoundJSON.put(fieldsJSON.start_time.toString(), start.getStart_time());
					outBoundJSON.put(fieldsJSON.stop_time.toString(), start.getStop_time());
					
					UsersDAO users = new UsersDAO();
					
					List<Users> Rows = users.getAllRows();
					JSONArray arr = new JSONArray();

					int counter = 0;
					for (Users Row : Rows) {
		                JSONObject obj = new JSONObject();
		                if ((Row.getLevel()).equals(access.User.toString())) {
		                	counter++;
		                	obj.put("Id", Row.getId());
		                	obj.put(fieldsJSON.login.toString(), Row.getLogin());
		                	obj.put(fieldsJSON.level.toString(), Row.getLevel());
		                	obj.put(fieldsJSON.latitude.toString(), Row.getLatitude());
		                	obj.put(fieldsJSON.altitude.toString(), Row.getAltitude());
		                	obj.put(fieldsJSON.longitude.toString(), Row.getLongtitude());
		                	obj.put(fieldsJSON.master_mark_label.toString(), Row.getMaster_mark_label());
		                	obj.put(fieldsJSON.registred_start_id.toString() , Row.getRegistred_start_id());
		                	obj.put(fieldsJSON.registred_race_id.toString() , Row.getRegistred_race_id());

		                	arr.put(obj);	
		                }					
					}
					
		            outBoundJSON.put(fieldsJSON.usersArr.toString(), arr);
		            outBoundJSON.put(fieldsJSON.counter.toString(), counter);
		         
				}
				else {		outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
		} 
		catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
		return outBoundJSON.toString();
	}
	
	

}