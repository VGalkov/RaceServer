package ru.galkov.raceserver.hibernate.modelDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import ru.galkov.raceserver.hibernate.dao.users_interface;
import ru.galkov.raceserver.hibernate.model.Users;
import ru.galkov.raceserver.utilites.HibernateUtil;

public class UsersDAO implements users_interface {
    private Session session;
    
    public UsersDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

	@Override
	public void addRow(Users user) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
		
	}

	@Override
	public void updateRow(Users user) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
		
	}

	@Override
	public Users getRowById(Long id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		Users user = null;
		user = (Users) session.load(Users.class, id);
        return user;
	}

	@Override
	public Collection<Users> getAllRows() throws SQLException, Exception {
		// TODO Auto-generated method stub
        List<Users> users = new ArrayList<Users>();
        
        users = session.createCriteria(Users.class).list();
        return users;
	}

	@Override
	public void deleteRow(Users user) throws SQLException, Exception {
		// TODO Auto-generated method stub
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
	}

	@Override
	public Users getRowByLogin(String login) throws SQLException, Exception {
		// TODO Auto-generated method stub
		Users user = null;
		user = (Users) session.load(Users.class, login);
        return user;
	}





}
