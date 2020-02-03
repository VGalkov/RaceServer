package ru.galkov.raceserver;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public interface Configuration {
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.####");
	public static final int USER_GEO_TIMER = 600;
	public static final int USER_GEO_FREQ =450;
	public static final Key serverKey = new Key();	
	public static final String resultDirectory = "results";
	public static final SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    //  -Djava.library.path=/usr/lib/i386-linux-gnu/
    public static final String connectionUrl = "jdbc:mysql://localhost:3306/racetracer";
	//public static final String connectionUrl = "jdbc:mysql://185.251.240.7:3306/racetracer";
	//public static final String connectionUrl = "jdbc:mysql://10.225.0.2:3306/racetracer";
	public static final String connectionUser = "galkov";
	public static final String connectionPassword = "rs239";
}
