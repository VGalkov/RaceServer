package ru.galkov.raceserver.ActivityResponders;

	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.image.BufferedImage;
import java.util.List;

import ru.galkov.raceserver.RaceServerApplication.img_types;
import ru.galkov.raceserver.db_access.MainLogDAO;
import ru.galkov.raceserver.db_access.RaceDAO;
import ru.galkov.raceserver.db_access.StartDAO;
import ru.galkov.raceserver.db_access.UsersDAO;
import ru.galkov.raceserver.db_access.model.MainLog;
import ru.galkov.raceserver.db_access.model.Race;
import ru.galkov.raceserver.db_access.model.Start;
import ru.galkov.raceserver.db_access.model.Users;

	/*
	 *@galkov
	 создаёт рисунок результатов текущего старта. 
	 удобно видеть положение в турнире в реалтайме.
	 * */
	

	public class Graphic {
	
		private String imgSource = img_types.ALL.toString();
		//private String imgType = img_types.ALL.toString();
		private int ImageWidth = 500;
		private int ImageHeight = 500;
		private int ramka = 3;
		public void Close(){	}
		
		Graphic(){	}
		
		public void setImageWidth(int val) {
			ImageWidth = val;
		}
		
		public void ImageHeight(int val) {
			ImageHeight = val;
		}
		
		public void setIMGType(String imgType1, String exec_login2) {
			imgType = imgType1;
			if (imgType1.equals(img_types.LOGIN.toString())) 	imgSource = exec_login2;  
			else 												imgSource = imgType1;
		}
		
		
		public BufferedImage DrawDefault() {	
			
			if (!imgSource.equals(img_types.ALL.toString())) {
				
				RaceDAO rDAO = new RaceDAO(); 
				Race rID = rDAO.getActiveRace();
				long race_id = rID.getId();
				
				StartDAO sDAO = new StartDAO(); 
				Start sID = sDAO.getActiveStart();
				long start_id =  sID.getId();
				
				UsersDAO uDAO = new UsersDAO();
				Users u = uDAO.getRowIdByLogin(imgSource);
				
				MainLogDAO mainLog = new MainLogDAO();
				List<MainLog> Rows = mainLog.getRecordsOfCurrentStart(u, race_id, start_id);
				// здесь список регистраций текущего старта.
				// на Y - время, X - точки в порядке прохождения. 
			}
			else {
				// тут графики всех участников. как расположить точки в одинаковом порядке для всех одинаково?
			}
			
	        BufferedImage b = new BufferedImage(ImageWidth, ImageHeight, BufferedImage.TYPE_BYTE_INDEXED);
	        Graphics gr = b.getGraphics();
	        gr.setColor(Color.white);
	        gr.fillRect(0, 0, b.getWidth(), b.getHeight());
	        
	        gr.setColor(Color.black);
	        gr.drawRect(ramka, ramka, ImageWidth-2*ramka, ImageHeight-2*ramka);
	        
	        Font currentFont = gr.getFont();
		    Font newFont = currentFont.deriveFont(currentFont.getSize()-4 );
		    gr.setFont(newFont);
		    gr.drawString("График результатов участников текущего старта.",(ImageWidth/2)-80,20);

	        gr.drawRect(ramka, 50, ImageWidth-2*ramka, ImageHeight-2*ramka);
	        
	        // draw grid
		    gr.setColor(Color.gray);        
	        for (int i = ramka; i<ImageWidth; i=i+(ImageWidth/10)) { gr.drawLine(i, 50, i, ImageHeight-2*ramka); }
	        for (int i = ramka; i<ImageHeight; i=i+(ImageHeight/10)) { gr.drawLine(ramka, 50+i, ImageWidth-2*ramka, 50+i);   }
	        
			return b;	
		}

	}