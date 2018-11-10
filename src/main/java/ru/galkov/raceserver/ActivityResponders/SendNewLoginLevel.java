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
		String exec_level ="Error", exec_login = "nobody", login = "", inLevel = "", clientKey = "", password = "", master_mark_label="";
		JSONObject outBoundJSON = new JSONObject();
		JSONObject inBoundJSON = null;
		
	try {		
		outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
		inBoundJSON = new JSONObject(inJSON);
				
		master_mark_label = inBoundJSON.getString(fieldsJSON.master_mark_label.toString());
		exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString()); 
		exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString()); 
		clientKey = inBoundJSON.getString(fieldsJSON.key.toString()); 
		inLevel = inBoundJSON.getString(fieldsJSON.level.toString()); 
		login = inBoundJSON.getString(fieldsJSON.login.toString()); 		
				
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