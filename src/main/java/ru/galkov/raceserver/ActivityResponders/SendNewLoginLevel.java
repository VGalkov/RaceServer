package ru.galkov.raceserver.ActivityResponders;


import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.*;
import ru.galkov.raceserver.db_access.UsersDAO;
import ru.galkov.raceserver.db_access.model.Users;

@RestController
public class SendNewLoginLevel extends AskRoot {
	
	private static final String ASKER = "SendUserLevel";
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		String login = "", inLevel = "", password = "", master_mark_label="";

		Double altitude = 0.0, latitude = 0.0, longtitude = 0.0;
		
	try {		
		outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
		inBoundJSON = new JSONObject(inJSON);
				
		master_mark_label = inBoundJSON.getString(fieldsJSON.master_mark_label.toString());
		setSecurityVars(inBoundJSON);
		inLevel = inBoundJSON.getString(fieldsJSON.level.toString()); 
		login = inBoundJSON.getString(fieldsJSON.login.toString());
		latitude = inBoundJSON.getDouble(fieldsJSON.latitude.toString());	
		altitude = inBoundJSON.getDouble(fieldsJSON.altitude.toString());	
		longtitude = inBoundJSON.getDouble(fieldsJSON.longitude.toString());	
				
		if (serverKey.chkFileKey(clientKey))	{
			// ввести проверку уровня доступа или нужно запускать security
			outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
			UsersDAO u = new UsersDAO();
			Users user1 = u.getRowIdByLogin(login);
			password = user1.getPassword();
			long iD = user1.getId();
			
			
			if (inLevel.equals(access.Delete.toString())) {
				u = new UsersDAO();
				Users user = new Users();
				user.setLogin(login);
				user.setId(iD);
				u.deleteRow(user);
			}
			else {
			
				Users user = new Users();
				user.setLevel(inLevel);
				user.setLogin(login);
				user.setId(iD);
				user.setPassword(password);
				user.setMaster_mark_label(master_mark_label);
				user.setAltitude(altitude);
				user.setLatitude(latitude);
				user.setLongtitude(longtitude);
				u = new UsersDAO();
				u.updateRow(user);
			}			
			outBoundJSON.put(fieldsJSON.login.toString(), trigger.TRUE);	
			outBoundJSON.put(fieldsJSON.level.toString(),inLevel); 			
		}
		else 	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");
	} 
	catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}	
	
	new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
	return outBoundJSON.toString();
}



}