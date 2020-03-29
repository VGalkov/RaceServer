package ru.galkov.raceserver.hibernate.modelDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import ru.galkov.raceserver.hibernate.dao.gps_points_interface;
import ru.galkov.raceserver.hibernate.model.GPSpoints;

import ru.galkov.raceserver.utilites.HibernateUtil;


public class GPSpointsDAO implements  gps_points_interface {

    private Session session;
    
    public GPSpointsDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

	@Override
	public void addRow(GPSpoints point) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.save(point);
        session.getTransaction().commit();
		
	}

	@Override
	public void updateRow(GPSpoints point) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.update(point);
        session.getTransaction().commit();
	}

	@Override
	public GPSpoints getRowById(Long id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		GPSpoints point = null;
		point = (GPSpoints) session.load(GPSpoints.class, id);
        return point;
	}

	
	@Override
	public Collection<GPSpoints> getAllRows() throws SQLException, Exception {
		// TODO Auto-generated method stub
        List<GPSpoints> points = new ArrayList<GPSpoints>();
        points = session.createCriteria(GPSpoints.class).list();
        return points;
	}

	@Override
	public void deleteRows(GPSpoints point) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.delete(point);
        session.getTransaction().commit();
	}


    
    
}


