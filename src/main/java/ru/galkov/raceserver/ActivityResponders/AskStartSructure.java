package ru.galkov.raceserver.ActivityResponders;

import java.util.List;
import org.json.*;
import org.springframework.web.bind.annotation.*;

import ru.galkov.raceserver.Enums;
import ru.galkov.raceserver.db_access.StartDAO;
import ru.galkov.raceserver.db_access.model.Start;
@RestController
public class AskStartSructure extends AskRoot implements Enums {

	private static final String ASKER = "AskStartSructure";
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		long race_id =0L;
		try {
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);
				setSecurityVars(inBoundJSON);
				
				race_id = inBoundJSON.getLong(fieldsJSON.race_id.toString());
				
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
		            outBoundJSON.put(fieldsJSON.race_id.toString(), race_id);	
					
	                StartDAO sDAO = new StartDAO(); 
	                List<Start> startRows = sDAO.getRaceIdRows(race_id);
		            JSONArray startsConfig = new JSONArray();
		                for (Start startRow : startRows) {
							
			                JSONObject objStart = new JSONObject();
			                objStart.put(fieldsJSON.start_id.toString(), startRow.getId());
			                objStart.put(fieldsJSON.active.toString(), startRow.getActive());
			                objStart.put(fieldsJSON.label.toString(), startRow.getLabel());
			                startsConfig.put(objStart);
		                }		                
	                outBoundJSON.put(fieldsJSON.startsConfig.toString(),startsConfig);
		            
				}
				else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");}
		}
		catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
		return outBoundJSON.toString();
	}
	
	

}
