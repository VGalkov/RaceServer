package ru.galkov.raceserver.ActivityResponders;


import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import ru.galkov.raceserver.RaceServerApplication;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;

public abstract class AskRoot {
	
	public ru.galkov.raceserver.Key serverKey = RaceServerApplication.serverKey;
	protected Logger logger = RaceServerApplication.logger;	
	protected String exec_level ="Error", exec_login = "nobody", clientKey = "";	
	
	protected JSONObject outBoundJSON = new JSONObject();
	protected JSONObject inBoundJSON = null;
	
	protected void workWithError(Exception e1 , String asker1)		{
			e1.printStackTrace();
			logger.error(asker1 + "Ошибка формата протокола. Не отработал");
		}
	
	protected void setSecurityVars(JSONObject inBoundJSON1) throws JSONException {
		clientKey = inBoundJSON1.getString(fieldsJSON.key.toString());
		exec_level = inBoundJSON1.getString(fieldsJSON.exec_level.toString());
		exec_login = inBoundJSON1.getString(fieldsJSON.exec_login.toString());
	}
}
