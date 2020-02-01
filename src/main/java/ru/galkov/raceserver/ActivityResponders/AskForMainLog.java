package ru.galkov.raceserver.ActivityResponders;


import java.util.List;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.db_access.MainLogDAO;
import ru.galkov.raceserver.db_access.model.MainLog;


@RestController
public class AskForMainLog extends AskRoot {

	private static final String ASKER = "AskForMainLog";	
	
	
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
					JSONArray arr = new JSONArray();
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
					MainLogDAO mainLog = new MainLogDAO();
					List<MainLog> Rows = mainLog.getAllRows();
					
					for (int i =0; i<Rows.size(); i++  ) {
						MainLog Row = Rows.get(i);
		                JSONObject obj = new JSONObject();
		                obj.put("Id", i);
		                obj.put(fieldsJSON.login.toString(), Row.getLogin());
		                obj.put(fieldsJSON.mark.toString(), Row.getMark_id());
		                obj.put(fieldsJSON.mark_label.toString(), Row.getMark_label());
		                obj.put(fieldsJSON.latitude.toString(), Row.getLatitude());
		                obj.put(fieldsJSON.start_id.toString(), Row.getStart_id());
		                obj.put(fieldsJSON.race_id.toString(), Row.getRace_id());
		                obj.put(fieldsJSON.longitude.toString(), Row.getLongtitude());
		                obj.put(fieldsJSON.altitude.toString(), Row.getAltitude());
		                obj.put(fieldsJSON.date.toString(), Row.getDt());

		                arr.put(obj);						
					}
		            outBoundJSON.put(fieldsJSON.rows.toString(), arr); 
		            mainLog.close();
				}
				else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
		}
		catch (JSONException e) { workWithError(e, ASKER); }		
		
		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);		
		return outBoundJSON.toString();

	}
	
}
