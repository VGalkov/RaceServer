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
	          
	            JSONArray arr = new JSONArray();
	            
	            UsersDAO uDAO = new UsersDAO();
	            List<Users> rowsUsers = uDAO.getAllRows();
	            				
				for (int i =0; i<rowsUsers.size(); i++  ) {
					Users Row = rowsUsers.get(i);
	                JSONObject obj = new JSONObject();
	                obj.put("Id", i);
	                obj.put(fieldsJSON.label.toString(), Row.getLogin());
	                obj.put(fieldsJSON.altitude.toString(), Row.getAltitude());
	                obj.put(fieldsJSON.longitude.toString(), Row.getLongtitude());
	                obj.put(fieldsJSON.latitude.toString(), Row.getLatitude());
	                obj.put(fieldsJSON.point_type.toString(), points_types.guest);
	                arr.put(obj);						
				}	            
	            
	            NFCmarksDAO nfcDAO = new NFCmarksDAO();
	            List<NFCmarks> rowsNFC = nfcDAO.getAllRows();

				for (int i =0; i<rowsNFC.size(); i++  ) {
					NFCmarks Row = rowsNFC.get(i);
	                JSONObject obj = new JSONObject();
	                obj.put("Id", i);
	                obj.put(fieldsJSON.label.toString(), Row.getLabel());
	                obj.put(fieldsJSON.altitude.toString(), Row.getAltitude());
	                obj.put(fieldsJSON.longitude.toString(), Row.getLongtitude());
	                obj.put(fieldsJSON.latitude.toString(), Row.getLatitude());
	                obj.put(fieldsJSON.point_type.toString(), points_types.mark);
	                arr.put(obj);						
				}
	            
	            outBoundJSON.put(fieldsJSON.rows.toString(), arr);
	            
	            //TODO список точек формируем и отправляем.
	            /*
	             * points_types
	            type = mark,user,admin,guest
	            {
	                "rows":[
	                {"longitude":0.0,"altitude":0.0,"latitude":0.0,"type":"","label":"1"},
	                {"longitude":0.0,"altitude":0.0,"latitude":0.0,"type":"","label":"1"},
	                "asker":"AskStartSructure",
	                "key":"galkovvladimirandreevich"
	            } */
			}		
			else
			outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");
		}

		catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}		

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);
		return outBoundJSON.toString();
	}
}
