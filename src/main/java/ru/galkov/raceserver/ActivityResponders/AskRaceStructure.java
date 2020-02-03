package ru.galkov.raceserver.ActivityResponders;

import java.util.List;
import org.json.*;
import org.springframework.web.bind.annotation.*;

import ru.galkov.raceserver.Enums;
import ru.galkov.raceserver.db_access.*;
import ru.galkov.raceserver.db_access.model.*;

@RestController
public class AskRaceStructure extends AskRoot implements Enums {

	private static final String ASKER = "AskRaceStructure";	
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {

		try {

				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);
				setSecurityVars(inBoundJSON);
				
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
					
		            RaceDAO rDAO = new RaceDAO();
					List<Race> Rows = rDAO.getAllRows();
					JSONArray racesConfig = new JSONArray();
					
					for (Race Row : Rows) {
		                JSONObject obj = new JSONObject();
		                obj.put(fieldsJSON.active.toString(), Row.getActive())
		                	.put(fieldsJSON.label.toString(), Row.getLabel())
		                	.put(fieldsJSON.race_id.toString(), Row.getId());
		                
		                StartDAO sDAO = new StartDAO(); 
		                List<Start> startRows = sDAO.getRaceIdRows(Row.getId());
		                JSONArray startsConfig = new JSONArray();
		                for (Start startRow : startRows) {
			                JSONObject objStart = new JSONObject();
			                objStart.put(fieldsJSON.start_id.toString(), startRow.getId())
			                	.put(fieldsJSON.active.toString(), startRow.getActive())
			                	.put(fieldsJSON.label.toString(), startRow.getLabel());
			                startsConfig.put(objStart);
		                }		                
		                obj.put(fieldsJSON.startsConfig.toString(),startsConfig);
		                
		                racesConfig.put(obj);
					}
					outBoundJSON.put(fieldsJSON.racesConfig.toString(), racesConfig);
		            
				}
				else {		outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
		} 
		catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
		return outBoundJSON.toString();
	}
	
	

}
