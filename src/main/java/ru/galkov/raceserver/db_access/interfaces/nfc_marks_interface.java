package ru.galkov.raceserver.db_access.interfaces;

import java.util.List;
import ru.galkov.raceserver.db_access.model.NFCmarks;

public interface nfc_marks_interface extends EntytyAccess {
    public void addRow(NFCmarks mark);
    public void updateRow(NFCmarks mark);
    public NFCmarks getRowById(Long id);
    public List<NFCmarks> getAllRows();
    public void deleteRow(NFCmarks mark);

}
