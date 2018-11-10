package ru.galkov.raceserver.ActivityResponders;

import org.apache.log4j.Logger;

import ru.galkov.raceserver.RaceServerApplication;

public abstract class AskRoot {
	
	public ru.galkov.raceserver.Key serverKey = RaceServerApplication.serverKey;
	protected Logger logger = RaceServerApplication.logger;
	
}
