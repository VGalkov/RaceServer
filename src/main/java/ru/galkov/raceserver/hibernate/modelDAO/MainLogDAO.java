package ru.galkov.raceserver.hibernate.modelDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import ru.galkov.raceserver.hibernate.dao.main_log_interface;
import ru.galkov.raceserver.hibernate.model.MainLog;
import ru.galkov.raceserver.utilites.HibernateUtil;


public class MainLogDAO implements main_log_interface {
	
    private Session session;

    public MainLogDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

	@Override
	public void addRow(MainLog log) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.save(log);
        session.getTransaction().commit();
	}

	@Override
	public void updateRow(MainLog log) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.update(log);
        session.getTransaction().commit();
		
	}

	@Override
	public MainLog getRowById(Long id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		MainLog logRow = null;
		logRow = (MainLog) session.load(MainLog.class, id);
        return logRow;
	}

	@Override
	public Collection<MainLog> getAllRows() throws SQLException, Exception {
		// TODO Auto-generated method stub
        List<MainLog> logRows = new ArrayList<MainLog>();
        
        logRows = session.createCriteria(MainLog.class).list();
        return logRows;
	}

	@Override
	public void deleteRows(MainLog log) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.delete(log);
        session.getTransaction().commit();
	}



}
