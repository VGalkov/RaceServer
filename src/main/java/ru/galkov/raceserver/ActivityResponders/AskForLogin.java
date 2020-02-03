package ru.galkov.raceserver.ActivityResponders;

import org.json.*;
import org.springframework.web.bind.annotation.*;

import ru.galkov.raceserver.Enums;
import ru.galkov.raceserver.db_access.UsersDAO;
import ru.galkov.raceserver.db_access.model.Users;


@RestController
public class AskForLogin extends AskRoot implements Enums {

	private final String ASKER = "AskForLogin";
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		
		String login = "", password = "";
		UsersDAO uDAO;
		try {		
				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);						
				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());
				password = inBoundJSON.getString(fieldsJSON.password.toString());
				login = inBoundJSON.getString(fieldsJSON.login.toString());			

				if (serverKey.chkFileKey(clientKey))	{
					outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
					uDAO = new UsersDAO();
					Users user = uDAO.getRowIdByLogin(login);
					
					if (user != null) {
					
						if ((user.getLogin().equals(login)) && (user.getPassword().equals(password))) {
							outBoundJSON.put("login", trigger.TRUE);
							outBoundJSON.put(fieldsJSON.level.toString(), user.getLevel());
						}
						else {
							outBoundJSON.put("login", trigger.FALSE);
							outBoundJSON.put(fieldsJSON.level.toString(), access.Guest);
						}						
						
					}
					else {
						Users u = new Users();
						u.setLevel(access.Guest.toString());
						u.setLogin(login);
						u.setPassword(password);
						uDAO = new UsersDAO();
						uDAO.addRow(u);
						outBoundJSON.put("login", trigger.FALSE);
						outBoundJSON.put(fieldsJSON.level.toString(), access.Guest);
					}
				}
				else { outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!"); }				
		} 
		catch (JSONException e) { 	workWithError(e, ASKER); } 
		
		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);
		return outBoundJSON.toString();
	}

	
}
