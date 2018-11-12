package ru.galkov.raceserver.ActivityResponders;

import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.db_access.*;
import ru.galkov.raceserver.db_access.model.Start;

@RestController
public class SendActiveRaceStart extends AskRoot {
	private static final String ASKER = "SendActiveRaceStart";
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		String exec_level ="Error", exec_login = "nobody",  clientKey = "";	
		JSONObject outBoundJSON = new JSONObject();
		//TODO String -> Date!!!
		String start_time, stop_time;
		// Date start_time, stop_time;
		JSONObject inBoundJSON = null;
		long start_id = 0L, race_id = 0L;
		try {
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);
				
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());			
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				start_id = inBoundJSON.getLong(fieldsJSON.start_id.toString());
				race_id = inBoundJSON.getLong(fieldsJSON.race_id.toString());
				start_time = inBoundJSON.getString(fieldsJSON.start_time.toString());
				stop_time = inBoundJSON.getString(fieldsJSON.stop_time.toString());
				
				//stop_time = formatForDate.format(new Date()); 				
				if (serverKey.chkFileKey(clientKey))	{

					RaceDAO rDAO = new RaceDAO();
					rDAO.setActive(race_id);
					StartDAO sDAO = new StartDAO();
					Start start = sDAO.getRowById(start_id);
					
					sDAO = new StartDAO();
					start.setId(start_id);
					sDAO.updateRow(start);
					
					sDAO = new StartDAO();
					sDAO.setActive(start_id);
					 
					sDAO = new StartDAO();
					start = sDAO.getRowById(start_id);
					start.setStart_time(start_time);
					start.setStop_time(stop_time);
					
					sDAO = new StartDAO();
					sDAO.updateRow(start);
					
					
					sDAO = new StartDAO();
					start = sDAO.getRowById(start_id);
					
					outBoundJSON.put(fieldsJSON.race_id.toString(), race_id);
					outBoundJSON.put(fieldsJSON.start_id.toString(), start_id);
					outBoundJSON.put(fieldsJSON.start_time.toString(), start.getStart_time());
					outBoundJSON.put(fieldsJSON.stop_time.toString(), start.getStop_time());
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
				}
				else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
		} 
		catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}		

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
		return outBoundJSON.toString();
	}
	
}
