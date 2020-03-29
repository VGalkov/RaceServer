package ru.galkov.raceserver.hibernate.dao;

import java.sql.SQLException;
import java.util.Collection;

import ru.galkov.raceserver.hibernate.model.Users;

public interface users_interface {

    public void addRow(Users user) throws SQLException, Exception;
    public void updateRow(Users user) throws SQLException, Exception;
    public Users getRowById(Long id) throws SQLException, Exception;
    public Collection<Users> getAllRows() throws SQLException, Exception;
    public void deleteRow(Users user) throws SQLException, Exception;
    
    
    public Users getRowByLogin(String login) throws SQLException, Exception;

}
