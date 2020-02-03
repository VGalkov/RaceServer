package ru.galkov.raceserver.ActivityResponders;


import org.json.*;
import org.springframework.web.bind.annotation.*;

import ru.galkov.raceserver.Enums;
import ru.galkov.raceserver.db_access.*;
import ru.galkov.raceserver.db_access.model.Start;


public class SendNewRaceStart  extends AskRoot implements Enums {
	private final String ASKER = "SendNewRaceStart";
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		long start_id = 0L, race_id = 0L;

		String start_time, stop_time;
		try {
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);
				setSecurityVars(inBoundJSON);
				start_id = inBoundJSON.getLong(fieldsJSON.start_id.toString()); 
				race_id = inBoundJSON.getLong(fieldsJSON.race_id.toString()); 
				stop_time =  inBoundJSON.getString(fieldsJSON.stop_time.toString());
				start_time = inBoundJSON.getString(fieldsJSON.start_time.toString());
				
				
				
				if (serverKey.chkFileKey(clientKey))	{
					// ввести проверку уровня доступа или нужно запускать security
					RaceDAO rDAO = new RaceDAO();
					rDAO.setActive(race_id);
					
					StartDAO sDAO = new StartDAO();
					Start start = sDAO.getRowById(start_id);
					
					sDAO = new StartDAO();
					start.setId(start_id);
					start.setStart_time(start_time);
					start.setStop_time(stop_time);
					sDAO.updateRow(start);
					
					sDAO = new StartDAO();
					sDAO.setActive(start_id);
					
					outBoundJSON.put(fieldsJSON.start_id.toString(), start_id);
					outBoundJSON.put(fieldsJSON.race_id.toString(), race_id);
					outBoundJSON.put(fieldsJSON.start_time.toString(), start_time);
					outBoundJSON.put(fieldsJSON.stop_time.toString(), stop_time);
					outBoundJSON.put(fieldsJSON.status.toString(), trigger.TRUE);
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey()); 
				}
				else { outBoundJSON.put(fieldsJSON.error.toString(), fieldsJSON.key + "- не верный!"); }
		} 
		catch (JSONException e) { workWithError(e, ASKER); }			
		
		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);		
		return outBoundJSON.toString();
	}
	
	
}
