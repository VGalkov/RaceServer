package ru.galkov.raceserver.ActivityResponders;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import ru.galkov.raceserver.RaceServerApplication;
import ru.galkov.raceserver.RaceServerApplication.*;
import ru.galkov.raceserver.db_access.*;
import ru.galkov.raceserver.db_access.model.*;


@RestController
public class AskResultsTable extends AskRoot {

	private static final String ASKER = "AskResultsTable";
	private SimpleDateFormat formatForDate = RaceServerApplication.formatForDate;
	private final String resultDirectory = RaceServerApplication.resultDirectory;
	
	
	@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
	@ResponseBody
	public String makeAnswer(@PathVariable("inJSON") String inJSON, 
				final HttpServletRequest request,
				final HttpServletResponse response) {
		String fileType ="";

		try {

				outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
				inBoundJSON = new JSONObject(inJSON);

				clientKey = inBoundJSON.getString(fieldsJSON.key.toString());		
				exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
				exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
				fileType = inBoundJSON.getString(fieldsJSON.fileType.toString());
								
				if (serverKey.chkFileKey(clientKey))	{
		            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
		            
		    		Random random = new Random();
		    		String fileName =  fileType+"_"+random.nextInt(1000000)+".csv";

		            outBoundJSON.put(fieldsJSON.resultsFileLink.toString(), resultDirectory+"/" +fileName);
		            outBoundJSON.put(fieldsJSON.resultsFileDir.toString(), resultDirectory);
		    		
		            File file = new File (resultDirectory+"/" +fileName);
		            generateFile(fileType, resultDirectory+"/" +fileName);

		            try (InputStream fileInputStream = new FileInputStream(file);
		                OutputStream output = response.getOutputStream();) {
		                response.reset();
		                response.setContentType("application/octet-stream");
		                // response.setContentType("application/csv"); //???
		                response.setContentLength((int) (file.length()));
		                response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		                IOUtils.copyLarge(fileInputStream, output);
		                output.flush();
		            } catch (IOException e) {
		                e.getMessage();
		            }
		            catch (Exception e) {    e.getMessage();  }		            
				}
				else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
		} 
		catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}

		new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);
		return outBoundJSON.toString();
	}
	
	private void generateFile(String fType1, String fileNameFull)  {

	try {
		File file = new File(fileNameFull);
		if(file.exists()) file.delete();
		file.createNewFile();
		PrintWriter out = new PrintWriter(file.getAbsoluteFile());
		
		if  (fType1.equals(fileType.Results.toString())) {
			
			MainLogDAO mainLog = new MainLogDAO();
			List<MainLog> mainLogRows = mainLog.getAllRows();
			out.print("'LogRowId'; 'datetime(yyyy-mm-dd hh:mm:ss)'; 'mark_label'; 'login'; 'race_id'; 'start_id'; 'altitude'; 'longtitude'; 'latitude'; 'masterMark'; 'marksDelta';'mark_master_altitude';'mark_master_latitude';'mark_master_longtitude'||");
			for(MainLog Row: mainLogRows) {
				String date = formatForDate.format(Row.getDt());
				out.print("'"+Row.getId()+"'; '"+date+"'; '"+Row.getMark_label()+"'; '"+Row.getLogin()+"'; '"+Row.getRace_id()+"'; '"
                		+ Row.getStart_id()+"'; '"+Row.getAltitude()+"'; '"+Row.getLongtitude()+"'; '"+Row.getLatitude()+"'; '"+Row.getMasterMarkLabel()+"'; '"+Row.getMasterMarkLabelDelta()+"';'"+Row.getMark_master_altitude()+"';'"+Row.getMark_master_latitude()+"';'"+Row.getMark_master_longtitude()+"';||");
 
            }
		}
		else if (fType1.equals(fileType.Marcs.toString())) {
			NFCmarksDAO marksNFC = new NFCmarksDAO();
			List<NFCmarks> marksRows = marksNFC.getAllRows();
			out.print("'mark_id'; 'label'; 'altitude'; 'longtitude'; 'latitude'; ||");
			for(NFCmarks Row: marksRows) {
				out.print("'"+Row.getId()+"'; '"
						+ Row.getLabel()+"'; '"+Row.getAltitude()+"'; '"+Row.getLongtitude()+"'; '"+Row.getLatitude()+"'; ||"); 					
			}
		}
		else {
			ActivityLogDAO activityLog = new ActivityLogDAO();
			List<ActivityLog> activityLogRows = activityLog.getAllRows();			
			out.print("'row_id'; 'asker'; 'timestamp'; 'login'; 'level'; 'client_request'; 'server_ansver';||");			
			for(ActivityLog Row: activityLogRows) {
				String date = formatForDate.format(Row.getDt());
				out.print("'"+Row.getId()+"'; '"+Row.getAsker()+"'; '"+date+"'; '"+Row.getLogin()+"'; '"+Row.getLevel()+"'; '"
						+ Row.getJson_in()+"'; '"+Row.getJson_out()+"';||"); 
			}
		}		
		out.close();
	} catch (IOException e) {  e.getMessage();   }
	
	}
	
	
}