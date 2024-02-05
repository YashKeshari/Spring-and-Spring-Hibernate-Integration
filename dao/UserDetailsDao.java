package com.nagarro.advancedJava.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.nagarro.advancedJava.domain.UserDetails;

@Component
public class UserDetailsDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate; 
	
	//get all users from table
	public List<UserDetails> getAll(){

		List<UserDetails> users = this.hibernateTemplate.loadAll(UserDetails.class);		
		return users;
	}
	
	// returns if username and password are matching
	public boolean getUser(String username, String password) {
		
		SessionFactory sessionFactory = this.hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		List<UserDetails> matchedUser = new ArrayList<UserDetails>();
		for(Object oneObject : session.createQuery(
							   "from UserDetails u where u.username=:username and u.password=:password")
								.setParameter("username", username).setParameter("password", password)
								.getResultList()){
									matchedUser.add((UserDetails)oneObject);
		}
		
		if(matchedUser.isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}
}
