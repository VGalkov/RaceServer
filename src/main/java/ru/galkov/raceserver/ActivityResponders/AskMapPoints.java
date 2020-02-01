package ru.galkov.raceserver.ActivityResponders;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.RaceServerApplication.points_types;
import ru.galkov.raceserver.db_access.NFCmarksDAO;
import ru.galkov.raceserver.db_access.UsersDAO;
import ru.galkov.raceserver.db_access.model.NFCmarks;
import ru.galkov.raceserver.db_access.model.Users;

@RestController
public class AskMapPoints extends AskRoot {

	private static final String ASKER = "AskMapPoints";	
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		
		try {
			outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
			inBoundJSON = new JSONObject(inJSON);
			clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
			exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());				
			exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
			
			if (!exec_login.equals("nobody")) {
			
			if (serverKey.chkFileKey(clientKey))	{
	            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
	          
	            JSONArray arr = new JSONArray();	            
	            UsersDAO uDAO = new UsersDAO();
	            List<Users> rowsUsers = uDAO.getAllRows();
	            for(Users Row: rowsUsers) {
	                JSONObject obj = new JSONObject();
	                obj.put(fieldsJSON.label.toString(), Row.getLogin());
	                obj.put(fieldsJSON.altitude.toString(), Row.getAltitude());
	                obj.put(fieldsJSON.longitude.toString(), Row.getLongtitude());
	                obj.put(fieldsJSON.latitude.toString(), Row.getLatitude());
	                obj.put(fieldsJSON.point_type.toString(), points_types.guest);
	                arr.put(obj);						
				}	            
	            
	            NFCmarksDAO nfcDAO = new NFCmarksDAO();
	            List<NFCmarks> rowsNFC = nfcDAO.getAllRows();
				for (NFCmarks Row: rowsNFC) {
	                JSONObject obj = new JSONObject();
	                obj.put(fieldsJSON.label.toString(), Row.getLabel());
	                obj.put(fieldsJSON.altitude.toString(), Row.getAltitude());
	                obj.put(fieldsJSON.longitude.toString(), Row.getLongtitude());
	                obj.put(fieldsJSON.latitude.toString(), Row.getLatitude());
	                obj.put(fieldsJSON.point_type.toString(), points_types.mark);
	                arr.put(obj);						
				}
	            
	            outBoundJSON.put(fieldsJSON.rows.toString(), arr);
			}		
			else			outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");
			}
		}

		catch (JSONException e) { workWithError(e, ASKER); }		
		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);
		return outBoundJSON.toString();
	}
}
