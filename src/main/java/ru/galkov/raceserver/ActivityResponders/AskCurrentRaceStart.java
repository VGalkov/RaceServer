package ru.galkov.raceserver.ActivityResponders;

import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.*;
import ru.galkov.raceserver.db_access.*;
import ru.galkov.raceserver.db_access.model.*;

@RestController
public class AskCurrentRaceStart extends AskRoot {

	private final String ASKER = "AskCurrentRaceStart"; // this.toString().substring(0,this.toString().indexOf('@'));

	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {

		try {		
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);				
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());				
				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				
				if (serverKey.chkFileKey(clientKey))	{
					
					outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
					RaceDAO rDAO = new RaceDAO();
					Race race = rDAO.getActiveRace();
					StartDAO sDAO = new StartDAO();
					Start start = sDAO.getActiveStart();
				
					try {  
							UsersDAO uDAO = new UsersDAO();
							Users u = uDAO.getRowIdByLogin(exec_login);
							u.setRegistred_race_id(race.getId());
							u.setRegistred_start_id(start.getId());
							
							uDAO = new UsersDAO();
							uDAO.updateRow(u);
							
							outBoundJSON.put(fieldsJSON.race_id.toString(), race.getId());
							outBoundJSON.put(fieldsJSON.race_name.toString(), race.getLabel());
							outBoundJSON.put(fieldsJSON.start_id.toString(), start.getId());
							outBoundJSON.put(fieldsJSON.start_label.toString(), start.getLabel());
							outBoundJSON.put(fieldsJSON.start_time.toString(),start.getStart_time() );
							outBoundJSON.put(fieldsJSON.stop_time.toString(),start.getStop_time() );
							outBoundJSON.put(fieldsJSON.status.toString(), trigger.TRUE);
					}
				
					catch (NullPointerException e) {
							outBoundJSON.put(fieldsJSON.status.toString(), trigger.FALSE);
							outBoundJSON.put(fieldsJSON.race_id.toString(), 0L);
							outBoundJSON.put(fieldsJSON.start_id.toString(), 0L);	
					}
				}
				else outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");
				

		} 
		catch (JSONException e) { workWithError(e, ASKER);	 } 
		
		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);
		return outBoundJSON.toString();
	}
	
		
}
