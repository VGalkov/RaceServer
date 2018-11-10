package ru.galkov.raceserver.ActivityResponders;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.*;
import org.springframework.web.bind.annotation.*;

import ru.galkov.raceserver.RaceServerApplication;
import ru.galkov.raceserver.RaceServerApplication.fieldsJSON;
import ru.galkov.raceserver.db_access.UsersDAO;
import ru.galkov.raceserver.db_access.model.Users;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.*;

	@RestController
	public class AskResultsIMG extends AskRoot {

		private static final String ASKER = "AskResultsIMG";
		private final String resultDirectory = RaceServerApplication.resultDirectory;
		
		
		@RequestMapping(value = "/" + ASKER + "/{inJSON}") 
		@ResponseBody
		public String makeAnswer(@PathVariable("inJSON") String inJSON, 
					final HttpServletRequest request,
					final HttpServletResponse response) {
			String exec_level ="Error", exec_login = "nobody",  fileType ="", clientKey = "", imgType="ALL";
			JSONObject outBoundJSON = new JSONObject();
			JSONObject inBoundJSON = null;

			try {

					outBoundJSON.put(fieldsJSON.asker.toString(), ASKER);
					inBoundJSON = new JSONObject(inJSON);

					clientKey = inBoundJSON.getString(fieldsJSON.key.toString());		
					exec_level = inBoundJSON.getString(fieldsJSON.exec_level.toString());
					exec_login = inBoundJSON.getString(fieldsJSON.exec_login.toString());
					imgType = inBoundJSON.getString(fieldsJSON.IMGType.toString());
									
					if (serverKey.chkFileKey(clientKey))	{
			            outBoundJSON.put(fieldsJSON.key.toString(), serverKey.getFileKey());
						UsersDAO users = new UsersDAO();
						List<Users> Rows = null;

						if (imgType.equals("ALL" )) {	Rows = users.getAllRows();	  }
			            else   {   Rows.add(users.getRowIdByLogin(exec_login));       }
			            			    		
						Random random = new Random();
			    		String fileName =  fileType+"_"+random.nextInt(1000000)+".csv";
			            File file = new File (resultDirectory+"/" +fileName);
			            // TODO это костылявый метод... через запись на диск переписать без диска.. 
			            createPNGResults(Rows, resultDirectory+"/" +fileName);
			            
			            try (InputStream fileInputStream = new FileInputStream(file);
				                OutputStream output = response.getOutputStream();) {
				                response.reset();
				                response.setContentType("application/octet-stream");
				                response.setContentLength((int) (file.length()));
				                response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
				                IOUtils.copyLarge(fileInputStream, output);
				                output.flush();
				            } 
			            catch (IOException e)	{	e.getMessage();  }
				        catch (Exception e)		{	e.getMessage();  }
					}
					else {	outBoundJSON.put(fieldsJSON.error.toString(), clientKey + "- не верный!");	}
			} 
			catch (JSONException e) { 	e.printStackTrace();logger.error(ASKER + "Ошибка формата протокола. Не отработал");}

			new WriteLog(ASKER,inBoundJSON, outBoundJSON, exec_login, exec_level);
			return outBoundJSON.toString();
		}
		
		private void createPNGResults(List<Users> Rows1, String fileNameFull2){
			// yарисовать сам рисунок... 
		    BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
		    Graphics g = bi.getGraphics(); 
			try {			
					File file = new File(fileNameFull2);
					if(file.exists()) file.delete();
					file.createNewFile();
			        ImageIO.write(bi,"png", new File(fileNameFull2));
					
			} catch (IOException e) {  e.getMessage();   }
		
	}
}