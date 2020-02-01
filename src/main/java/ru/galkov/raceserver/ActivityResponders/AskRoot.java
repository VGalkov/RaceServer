package ru.galkov.raceserver.ActivityResponders;


import org.apache.log4j.Logger;
import ru.galkov.raceserver.RaceServerApplication;

public abstract class AskRoot {
	
	public ru.galkov.raceserver.Key serverKey = RaceServerApplication.serverKey;
	protected Logger logger = RaceServerApplication.logger;	
	protected String exec_level ="Error", exec_login = "nobody", clientKey = "";	
	
	protected void workWithError(Exception e1 , String asker1)		{
			e1.printStackTrace();
			logger.error(asker1 + "Ошибка формата протокола. Не отработал");
		}
}
