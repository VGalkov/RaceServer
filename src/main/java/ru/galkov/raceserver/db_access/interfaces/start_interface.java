package ru.galkov.raceserver.db_access.interfaces;

import java.util.List;
import ru.galkov.raceserver.db_access.model.Start;

public interface start_interface extends EntytyAccess {

    public void addRow(Start start);
    public void updateRow(Start start);
    public Start getRowById(Long id);
    public Start getActiveStart();
    public List<Start> getAllRows();
    public List<Start> getRaceIdRows(long race_id);
    public void deleteRow(Start start);
    public void setActive(Long id);
}
