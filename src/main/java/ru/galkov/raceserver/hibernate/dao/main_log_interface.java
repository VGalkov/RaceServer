package ru.galkov.raceserver.hibernate.dao;

import java.sql.SQLException;
import java.util.Collection;

import ru.galkov.raceserver.hibernate.model.MainLog;



public interface main_log_interface {
    public void addRow(MainLog log) throws SQLException, Exception;
    public void updateRow(MainLog log) throws SQLException, Exception;
    public MainLog getRowById(Long id) throws SQLException, Exception;
    public Collection<MainLog> getAllRows() throws SQLException, Exception;
    public void deleteRows(MainLog log) throws SQLException, Exception;
}
