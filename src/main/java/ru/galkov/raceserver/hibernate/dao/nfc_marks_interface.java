package ru.galkov.raceserver.hibernate.dao;

import java.sql.SQLException;
import java.util.Collection;

import ru.galkov.raceserver.hibernate.model.NFCmarks;


public interface nfc_marks_interface {
    public void addRow(NFCmarks mark) throws SQLException, Exception;
    public void updateRow(NFCmarks mark) throws SQLException, Exception;
    public NFCmarks getRowById(Long id) throws SQLException, Exception;
    public Collection<NFCmarks> getAllRows() throws SQLException, Exception;
    public void deleteRow(NFCmarks mark) throws SQLException, Exception;
}
