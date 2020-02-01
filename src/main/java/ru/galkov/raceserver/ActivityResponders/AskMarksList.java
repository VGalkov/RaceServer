package ru.galkov.raceserver.ActivityResponders;

import java.util.List;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.db_access.NFCmarksDAO;
import ru.galkov.raceserver.db_access.model.NFCmarks;


@RestController
public class AskMarksList extends AskRoot{

	private static final String ASKER = "AskMarksList";
		
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		
		try {
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);

				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());				
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
				
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
					NFCmarksDAO marksNFC = new NFCmarksDAO();
					List<NFCmarks> Rows = marksNFC.getAllRows();
					
					JSONArray arr = new JSONArray();
					
					for (int i =0; i<Rows.size(); i++  ) {
						NFCmarks Row = Rows.get(i);
		                JSONObject obj = new JSONObject();

		                obj.put("Id", i);
		                obj.put(fieldsJSON.label.toString(), Row.getLabel());
		                obj.put(fieldsJSON.race_id.toString(), Row.getRace_id());
		                obj.put(fieldsJSON.altitude.toString(), Row.getAltitude());
		                obj.put(fieldsJSON.longitude.toString(), Row.getLongtitude());
		                obj.put(fieldsJSON.latitude.toString(), Row.getLatitude());
		                arr.put(obj);						
					}
		            outBoundJSON.put(fieldsJSON.rows.toString(), arr);
				}
				else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!"); }
		} 
		catch (JSONException e) { workWithError(e, ASKER); }		

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);
		return outBoundJSON.toString();

	}

}
