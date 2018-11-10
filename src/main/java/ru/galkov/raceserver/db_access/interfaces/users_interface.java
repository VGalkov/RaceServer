package ru.galkov.raceserver.db_access.interfaces;

import java.util.List;
import ru.galkov.raceserver.db_access.model.Users;

public interface users_interface extends EntytyAccess {
	
    public void addRow(Users user);
    public void updateRow(Users user);
    public Users getRowById(Long id);
    public List<Users> getAllRows();
    public void deleteRow(Users user);   
}
