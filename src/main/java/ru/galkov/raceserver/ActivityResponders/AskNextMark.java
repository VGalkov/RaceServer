package ru.galkov.raceserver.ActivityResponders;

import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.RaceServerApplication.trigger;
import ru.galkov.raceserver.db_access.NFCmarksDAO;
import ru.galkov.raceserver.db_access.model.NFCmarks;

//  возвращает ближайшую следующую метку.

@RestController
public class AskNextMark extends AskRoot {

	private static final String ASKER = "AskNextMark";	
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		
		JSONObject outBoundJSON = new JSONObject();
		JSONObject inBoundJSON = null;
		int counter = 0;
		try {
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);

				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
				try { counter = inBoundJSON.getInt(fieldsJSON.counter.toString()); }
				catch (JSONException e) { e.printStackTrace(); }
				
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());

					NFCmarksDAO markDAO = new NFCmarksDAO();
					NFCmarks mrk = markDAO.getNext(counter);

					outBoundJSON.put(fieldsJSON.mark.toString(),mrk.getId());
					outBoundJSON.put(fieldsJSON.status.toString(),trigger.TRUE);
					outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
					
					outBoundJSON.put(fieldsJSON.label.toString(), mrk.getLabel());
					outBoundJSON.put(fieldsJSON.race_id.toString(), mrk.getRace_id());
					outBoundJSON.put(fieldsJSON.altitude.toString(), mrk.getAltitude());
					outBoundJSON.put(fieldsJSON.longitude.toString(), mrk.getLongtitude());
					outBoundJSON.put(fieldsJSON.latitude.toString(), mrk.getLatitude());							            
				}
				else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
		}
		catch (JSONException e) { workWithError(e, ASKER); }			

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
		return outBoundJSON.toString();
	}
	
	
}

