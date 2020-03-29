package ru.galkov.raceserver.ActivityResponders;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.galkov.raceserver.utilites.Key;


@RestController
public class SendUserLevel {
	private static final String ASKER = "SendUserLevel";
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}",	method = RequestMethod.POST) 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON) {
		
		
		JSONObject outBoundJSON = new JSONObject();
		try {
				Key serverKey = new Key();
				
				outBoundJSON.put("asker", ASKER);
				outBoundJSON.put("key", serverKey.getFileKey());

				
				JSONObject inBoundJSON = new JSONObject(inJSON);
				String clientKey = inBoundJSON.getString("key");
				if (serverKey.chkFileKey(clientKey)) {
					// проверка ключа... 
				}
				else {
					outBoundJSON.put("error", clientKey + "- не верный!");
				}
		
		
		} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		
		return outBoundJSON.toString();
	}

}