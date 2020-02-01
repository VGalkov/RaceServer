package ru.galkov.raceserver.db_access.interfaces;


import java.sql.ResultSet;
import org.apache.log4j.Logger;
import ru.galkov.raceserver.RaceServerApplication;

public interface EntytyAccess {
      
	public static Logger logger = RaceServerApplication.logger;
	
	public int count();
	public void close();
	ResultSet execute(String sql );
	void update(String sql );
	
	 default void workWithError(Exception e1, String str2) {
		e1.printStackTrace();
		logger.error(str2 + "Ошибка формата протокола. Не отработал");
	}
}
