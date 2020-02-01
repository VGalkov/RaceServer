package ru.galkov.raceserver.ActivityResponders;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import java.io.*;
import javax.imageio.ImageIO;
import javax.servlet.http.*;

	@RestController
	public class AskResultsImgTable extends AskRoot {

		private static final String ASKER = "AskResultsImgTable";
		
		
		@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
		@ResponseBody
		public String makeAnswer(@PathVariable("inJSON") String inJSON, 
					final HttpServletRequest request,
					final HttpServletResponse response) {
			String imgType="ALL";
			JSONObject outBoundJSON = new JSONObject();
			JSONObject inBoundJSON = null;

			try {

					outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
					inBoundJSON = new JSONObject(inJSON);
					//http://192.168.1.5:8080/AskResultsImgTable/{'exec_level':'Admin','exec_login':'+79272006026','asker':'AskResultsImgTable','level'='Admin','key':'galkovvladimirandreevich'}
					clientKey = inBoundJSON.getString(fieldsJSON.key.toString());		
					exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
					exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
					imgType = inBoundJSON.getString(fieldsJSON.IMGType.toString());
									
					if (serverKey.chkFileKey(clientKey))	{
			            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());			            			    		
						ByteArrayOutputStream os = new ByteArrayOutputStream();
		                Graphic image = new Graphic();
						image.setIMGType(imgType, exec_login);		                
		                ImageIO.write(image.DrawDefault(), "png", os);
		                
		                try {
		                		InputStream fileInputStream = new ByteArrayInputStream(os.toByteArray());
		                		OutputStream output = response.getOutputStream();
			                	response.reset();
			                	response.setContentType("image/jpeg");
					            IOUtils.copyLarge(fileInputStream, output);
					            output.flush();;
				            } 
			            catch (IOException e)	{	e.getMessage();  }
				        catch (Exception e)		{	e.getMessage();  }
					}
					else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
			} 
			catch (JSONException | IOException e) { workWithError(e, ASKER); }

			new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);
			return outBoundJSON.toString();
		}
		
}