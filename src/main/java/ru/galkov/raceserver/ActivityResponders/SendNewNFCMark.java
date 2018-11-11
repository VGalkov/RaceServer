package ru.galkov.raceserver.ActivityResponders;



import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.RaceServerApplication.trigger;
import ru.galkov.raceserver.db_access.NFCmarksDAO;
import ru.galkov.raceserver.db_access.model.NFCmarks;


@RestController
public class SendNewNFCMark extends AskRoot{
	private static final String ASKER = "SendNewNFCMark";


//	private Double altitude;

	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		Double altitude = 0.0, latitude = 0.0, longitude = 0.0;
		long race = 0;
//		int counter = 0;
		String exec_level ="Error", exec_login = "nobody", mark = "", clientKey = "";//, mark_type = marksTypes.normal.toString();
		JSONObject outBoundJSON = new JSONObject();
		JSONObject inBoundJSON = null;
	
	try {		
		outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
		inBoundJSON = new JSONObject(inJSON);	
		
		exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString()); 
		exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString()); 
		clientKey = inBoundJSON.getString(fieldsJSON.key.toString()); 
		mark = inBoundJSON.getString(fieldsJSON.mark.toString()); 
		race = inBoundJSON.getLong(fieldsJSON.race.toString()); 
		latitude = inBoundJSON.getDouble(fieldsJSON.latitude.toString());   
		longitude = inBoundJSON.getDouble(fieldsJSON.longitude.toString()); 
		altitude = inBoundJSON.getDouble(fieldsJSON.altitude.toString()); 
//		try { counter = inBoundJSON.getInt(fieldsJSON.counter.toString()); }
//		catch (JSONException e) { e.printStackTrace(); }
		
		if (serverKey.chkFileKey(clientKey))	{
			// ввести проверку уровня доступа или нужно запускать security
			outBoundJSON.put(fieldsJSON.mark.toString(),mark);
			outBoundJSON.put(fieldsJSON.status.toString(),trigger.TRUE);
			outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
			
			outBoundJSON.put(fieldsJSON.label.toString(), mark);
			outBoundJSON.put(fieldsJSON.race_id.toString(), race);
			outBoundJSON.put(fieldsJSON.altitude.toString(), altitude);
			outBoundJSON.put(fieldsJSON.longitude.toString(), longitude);
			outBoundJSON.put(fieldsJSON.latitude.toString(), latitude);
			
			NFCmarks markDB = new NFCmarks();
			markDB.setLabel(mark);
			markDB.setRace_id(race);
			markDB.setAltitude(altitude);
			markDB.setLatitude(latitude);
			markDB.setLongtitude(longitude);
//			markDB.setCounter(counter);
			
			NFCmarksDAO nfc = new NFCmarksDAO();
			nfc.addRow(markDB);
		}
		else 	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");

		} 
	catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}	
	
	new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);	
	return outBoundJSON.toString();
}


}