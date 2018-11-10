package ru.galkov.raceserver.ActivityResponders;


import java.util.List;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.db_access.UsersDAO;
import ru.galkov.raceserver.db_access.model.Users;


@RestController
public class AskUserTable extends AskRoot {
	
	private static final String ASKER = "AskUserTable";

	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		String exec_level ="Error", exec_login = "nobody",  clientKey = "";
		JSONObject outBoundJSON = new JSONObject();
		JSONObject inBoundJSON = null;

		try {
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);

				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
				
				if (serverKey.chkFileKey(clientKey))	{
					// ввести проверку уровня доступа или нужно запускать security 
					UsersDAO users = new UsersDAO();
					
					List<Users> Rows = users.getAllRows();
					JSONArray arr = new JSONArray();
					
					for (int i =0; i<Rows.size(); i++  ) {
						Users Row = Rows.get(i);
		                JSONObject obj = new JSONObject();

		                obj.put("Id", Row.getId());
		                obj.put(fieldsJSON.login.toString(), Row.getLogin());
		                obj.put(fieldsJSON.level.toString(), Row.getLevel());
		                obj.put(fieldsJSON.latitude.toString(), Row.getLatitude());
		                obj.put(fieldsJSON.altitude.toString(), Row.getAltitude());
		                obj.put(fieldsJSON.longitude.toString(), Row.getLongtitude());
		                obj.put(fieldsJSON.password.toString(), Row.getPassword());
		                arr.put(obj);						
					}
		            outBoundJSON.put(fieldsJSON.usersArr.toString(), arr);
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
 
				}
				else {	outBoundJSON.put(fieldsJSON.error.toString(), fieldsJSON.key + "- не верный!"); }
		} 
		catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}			
		
		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);		
		return outBoundJSON.toString();

	}
	

}
