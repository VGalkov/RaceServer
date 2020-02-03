package ru.galkov.raceserver.db_access.interfaces;


import java.util.List;
import ru.galkov.raceserver.db_access.model.MainLog;

public interface main_log_interface extends EntytyAccess {
	
    public void addRow(MainLog log);
    public void updateRow(MainLog log);
    public MainLog getRowById(Long id);
    public List<MainLog> getAllRows();
    public void deleteRows(MainLog log) ;
}
