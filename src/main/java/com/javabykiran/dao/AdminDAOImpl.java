package com.javabykiran.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javabykiran.model.Admin;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Admin> getAdmins() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("from Admin");
		List<Admin> admins = query.list();
		return admins;
	}

	@Override
	public List<Admin> checkLogin(String email, String pass) {
		Session currentSession = sessionFactory.getCurrentSession();

		List<Admin> admin = currentSession
				.createQuery("from Admin where " + "email='" + email + "' and password='" + pass + "'").list();

		return admin;
	}

	@Override
	public Admin saveAdmin(Admin theAdmin) {
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println(theAdmin);
		currentSession.save(theAdmin);
		return theAdmin;
	}

	@Override
	public boolean delete(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();

		Admin admin = (Admin) currentSession.get(Admin.class, theId);

		currentSession.delete(admin);
		return true;
	}

}