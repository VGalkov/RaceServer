package ru.galkov.raceserver.ActivityResponders;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;

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

				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);

				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
				
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
		            outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);		
						
		         
				}
				else {		outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
		} 
		catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
		return outBoundJSON.toString();
	}
	
	

}