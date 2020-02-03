package ru.galkov.raceserver;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RaceServerApplication implements Enums, Configuration {

	public static final Logger logger = Logger.getLogger(RaceServerApplication.class);		
	
	public static void main(String[] args) {
		SpringApplication.run(RaceServerApplication.class, args);
		logger.info("Сервер запущен!");		
	}
	
}
