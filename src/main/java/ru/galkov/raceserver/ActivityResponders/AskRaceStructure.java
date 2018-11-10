package ru.galkov.raceserver.ActivityResponders;

import java.util.List;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.db_access.*;
import ru.galkov.raceserver.db_access.model.*;

@RestController
public class AskRaceStructure extends AskRoot {

	private static final String ASKER = "AskRaceStructure";	
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		String exec_level ="Error", exec_login = "nobody", clientKey = "";	
		JSONObject outBoundJSON = new JSONObject();
		JSONObject inBoundJSON = null;
		try {

				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);

				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
				
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
		            outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);		
					
		            RaceDAO rDAO = new RaceDAO();
					List<Race> Rows = rDAO.getAllRows();
					JSONArray racesConfig = new JSONArray();
					
					for (int i =0; i<Rows.size(); i++  ) {
						Race Row = Rows.get(i);
		                JSONObject obj = new JSONObject();
		                obj.put("Id", i);
		                obj.put(fieldsJSON.active.toString(), Row.getActive());
		                obj.put(fieldsJSON.label.toString(), Row.getLabel());
		                obj.put(fieldsJSON.race_id.toString(), Row.getId());
		                // список стартов, привязанных к соревнованию.
		                StartDAO sDAO = new StartDAO(); 
		                List<Start> startRows = sDAO.getRaceIdRows(Row.getId());
		                JSONArray startsConfig = new JSONArray();
		                for (int j =0; j<startRows.size(); j++  ) {
							Start startRow = startRows.get(j);
			                JSONObject objStart = new JSONObject();
			                objStart.put("Id", j);
			                objStart.put(fieldsJSON.start_id.toString(), startRow.getId());
			                objStart.put(fieldsJSON.active.toString(), startRow.getActive());
			                objStart.put(fieldsJSON.label.toString(), startRow.getLabel());
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
