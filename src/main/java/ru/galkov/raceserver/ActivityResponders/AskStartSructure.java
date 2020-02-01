package ru.galkov.raceserver.ActivityResponders;

import java.util.List;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.db_access.StartDAO;
import ru.galkov.raceserver.db_access.model.Start;
@RestController
public class AskStartSructure extends AskRoot {

	private static final String ASKER = "AskStartSructure";
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		
		JSONObject outBoundJSON = new JSONObject();
		JSONObject inBoundJSON = null;
		long race_id =0L;
		try {

				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);

				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
				race_id = inBoundJSON.getLong(fieldsJSON.race_id.toString());
				
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
		            outBoundJSON.put(fieldsJSON.race_id.toString(), race_id);	
					
	                StartDAO sDAO = new StartDAO(); 
	                List<Start> startRows = sDAO.getRaceIdRows(race_id);
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
	                outBoundJSON.put(fieldsJSON.startsConfig.toString(),startsConfig);
		            
				}
				else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");}
		}
		catch (JSONException e) { workWithError(e, ASKER); }

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
		return outBoundJSON.toString();
	}
	
	

}
