package ru.galkov.raceserver.ActivityResponders;

import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.db_access.UsersDAO;
import ru.galkov.raceserver.db_access.model.Users;

@RestController
public class AskMasterMark extends AskRoot {

	private static final String ASKER = "AskMasterMark";	
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		
		try {
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);
				setSecurityVars(inBoundJSON);
				
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());					

		            UsersDAO u = new UsersDAO();
					Users user =  u.getRowIdByLogin(exec_login);
		            outBoundJSON.put(fieldsJSON.mark_label.toString(), user.getMaster_mark_label());
				}
				else {
					outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");
				}
		}
		catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}			

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
		return outBoundJSON.toString();
	}
	
	
}

