package ru.galkov.raceserver.hibernate.modelDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;


import ru.galkov.raceserver.hibernate.dao.race_interface;
import ru.galkov.raceserver.hibernate.model.Race;
import ru.galkov.raceserver.utilites.HibernateUtil;



public class RaceDAO implements race_interface {
	
    private Session session;
    
    public RaceDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

	@Override
	public void addRow(Race race) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.save(race);
        session.getTransaction().commit();
		
	}

	@Override
	public void updateRow(Race race) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.update(race);
        session.getTransaction().commit();
	}

	@Override
	public Race getRowById(Long id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		Race race = null;
		race = (Race) session.load(Race.class, id);
        return race;
	}
	

	@Override
	public Collection<Race> getAllRows() throws SQLException, Exception {
		// TODO Auto-generated method stub
        List<Race> race = new ArrayList<Race>();
        
        race = session.createCriteria(Race.class).list();
        return race;
	}

	@Override
	public void deleteRow(Race race) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.delete(race);
        session.getTransaction().commit();
	}


}
