package ru.galkov.raceserver.hibernate.dao;

import java.sql.SQLException;
import java.util.Collection;

import ru.galkov.raceserver.hibernate.model.Race;


public interface race_interface {
	
    public void addRow(Race race) throws SQLException, Exception;
    public void updateRow(Race race) throws SQLException, Exception;
    public Race getRowById(Long id) throws SQLException, Exception;
    public Collection<Race> getAllRows() throws SQLException, Exception;
    public void deleteRow(Race race) throws SQLException, Exception;
}
