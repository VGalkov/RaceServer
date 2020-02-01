package ru.galkov.raceserver.db_access.interfaces;


import java.util.List;
import ru.galkov.raceserver.db_access.model.ActivityLog;

public interface activity_log_interface extends EntytyAccess {
	
    public void addRow(ActivityLog log);
    public ActivityLog getRowById(Long id);
    public List<ActivityLog> getAllRows();
}
