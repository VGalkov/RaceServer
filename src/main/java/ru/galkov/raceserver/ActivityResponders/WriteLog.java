package ru.galkov.raceserver.ActivityResponders;

import org.json.JSONObject;
import ru.galkov.raceserver.db_access.ActivityLogDAO;
import ru.galkov.raceserver.db_access.model.ActivityLog;

public class WriteLog {
	
		
	WriteLog(String ASKER,JSONObject in1, JSONObject out2, String login, String level) {
				
		ActivityLog aL = new ActivityLog();
		aL.setAsker(ASKER);
		aL.setLevel(level);
		aL.setLogin(login);
		aL.setJson_in(in1);
		aL.setJson_out(out2);
		ActivityLogDAO aLog =  new ActivityLogDAO();
		aLog.addRow(aL);	
	}
	
	
}
