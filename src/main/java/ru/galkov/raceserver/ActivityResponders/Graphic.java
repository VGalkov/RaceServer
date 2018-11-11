package ru.galkov.raceserver.ActivityResponders;

	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.image.BufferedImage;

	/*
	 *@galkov
	 создаёт рисунок результатов текущего старта. 
	 удобно видеть положение в турнире в реалтайме.
	 * */
	
	public class Graphic {
		public void Close(){	}
		
		Graphic(){	}
		
		public BufferedImage DrawDefault() {
//TODO передавать сюда нужный размер 
			int ImageWidth = 520;
			int ImageHeight = 220;
			
			int ramka = 3;
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
	        for (int i = ramka; i<ImageHeight; i=i+(ImageHeight/5)) { gr.drawLine(ramka, 50+i, ImageWidth-2*ramka, 50+i);   }
	        
			return b;	
		}

	}