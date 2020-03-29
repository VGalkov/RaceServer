package ru.galkov.raceserver.hibernate.dao;

import java.sql.SQLException;
import java.util.Collection;

import ru.galkov.raceserver.hibernate.model.GPSpoints;


public interface gps_points_interface {
	
    public void addRow(GPSpoints point) throws SQLException, Exception;
    public void updateRow(GPSpoints point) throws SQLException, Exception;
    public GPSpoints getRowById(Long id) throws SQLException, Exception;
    public Collection<GPSpoints> getAllRows() throws SQLException, Exception;
    public void deleteRows(GPSpoints point) throws SQLException, Exception;
    

}
