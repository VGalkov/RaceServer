package ru.galkov.raceserver.db_access.interfaces;


import java.util.List;
import ru.galkov.raceserver.db_access.model.Race;

public interface race_interface extends EntytyAccess {
	
    public void addRow(Race race);
    public void updateRow(Race race);
    public Race getRowById(Long id);
    public Race getActiveRace();
    public List<Race> getAllRows();
    public void deleteRow(Race race);
    public void setActive(Long id);

}
