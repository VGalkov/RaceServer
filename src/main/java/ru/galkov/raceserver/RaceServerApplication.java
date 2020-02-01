package ru.galkov.raceserver;

import java.text.*;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RaceServerApplication {

	public static final Logger logger = Logger.getLogger(RaceServerApplication.class);
	public static final Key serverKey = new Key();	
	public static final String resultDirectory = "results";
	public static final SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.####");
	public static final int USER_GEO_TIMER = 600;
	public static final int USER_GEO_FREQ =450;
	public static enum fieldsJSON {registred_race_id,registred_start_id,point_type,stop_time,start_time,IMGType,counter,topic,master_mark_delta,mark_master_latitude,mark_master_altitude,mark_master_longitude, master_mark_label, mark_type,resultsFileDir,resultsFileLink, exec_login,exec_level,start, asker,password, rows, date, key, mark, marks, error, usersArr, login, level, status, latitude, altitude,longitude, label, race_id, gps_point_id, race, race_name, start_id, start_label, mark_label, name, active, racesConfig, startsConfig, fileType}
	public static enum trigger {TRUE, FALSE}
	public static enum img_types {ALL, LOGIN}
	public static enum access {Admin, User, Guest, Error,Delete}
    public enum points_types {mark,master_mark,user,guest,admin,unknown}
    public enum fileType {Results, Marcs, Log}
    public enum marksTypes {master, normal}
	
    //  -Djava.library.path=/usr/lib/i386-linux-gnu/
    public static final String connectionUrl = "jdbc:mysql://localhost:3306/racetracer";
	//public static final String connectionUrl = "jdbc:mysql://185.251.240.7:3306/racetracer";
	//public static final String connectionUrl = "jdbc:mysql://10.225.0.2:3306/racetracer";
	public static final String connectionUser = "galkov";
	public static final String connectionPassword = "rs239";	
	
	public static void main(String[] args) {
		SpringApplication.run(RaceServerApplication.class, args);
		logger.info("Сервер запущен!");		
	}
	
}
