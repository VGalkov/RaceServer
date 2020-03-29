package ru.galkov.raceserver.hibernate.modelDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import ru.galkov.raceserver.hibernate.dao.nfc_marks_interface;
import ru.galkov.raceserver.hibernate.model.NFCmarks;
import ru.galkov.raceserver.utilites.HibernateUtil;

public class NFCmarksDAO implements nfc_marks_interface {

    private Session session;
    public NFCmarksDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
	@Override
	public void addRow(NFCmarks mark) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.save(mark);
        session.getTransaction().commit();
	}
	@Override
	public void updateRow(NFCmarks mark) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.update(mark);
        session.getTransaction().commit();
	}
	@Override
	public NFCmarks getRowById(Long id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		NFCmarks mark = null;
		mark = (NFCmarks) session.load(NFCmarks.class, id);
        return mark;
	}
	@Override
	public Collection<NFCmarks> getAllRows() throws SQLException, Exception {
		// TODO Auto-generated method stub
        List<NFCmarks> marks = new ArrayList<NFCmarks>();
        
        marks = session.createCriteria(NFCmarks.class).list();
        return marks;
	}
	@Override
	public void deleteRow(NFCmarks mark) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.delete(mark);
        session.getTransaction().commit();
	}

	


}
