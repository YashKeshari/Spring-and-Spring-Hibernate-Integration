package com.nagarro.advancedJava.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.nagarro.advancedJava.domain.TShirtDetails;

@Component
public class TShirtDao {
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	//save objects in table
	@Transactional
	public void save(TShirtDetails t) {
		this.hibernateTemplate.save(t);
	}
	
	//get all products from table
	public List<TShirtDetails> getAll(){
		List<TShirtDetails> products = this.hibernateTemplate.loadAll(TShirtDetails.class);		
		return products;
	}
	
	//filter products
	public List<TShirtDetails> matchingData(String color, String size, String gender, String preference){
		List<TShirtDetails> matchedShirts = new ArrayList<TShirtDetails>();
		SessionFactory sessionFactory= this.hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		switch(preference) {
		
		case "Price":	for(Object oneObject :session.createQuery(
						"from TShirtDetails t where t.color=:color and t.size=:size and t.gender=:gender order by t.price asc")
						.setParameter("color", color).setParameter("size", size).setParameter("gender", gender)
						.getResultList()){
							matchedShirts.add((TShirtDetails)oneObject);
						}						
						break;
		case "Rating":	for(Object oneObject : session.createQuery(
						"from TShirtDetails t where t.color=:color and t.size=:size and t.gender=:gender order by t.rating desc")
						.setParameter("color", color).setParameter("size", size).setParameter("gender", gender)
						.getResultList()){
							matchedShirts.add((TShirtDetails)oneObject);
						}						
						break;
		case "Both":	for(Object oneObject : session.createQuery(
						"from TShirtDetails t where t.color=:color and t.size=:size and t.gender=:gender order by t.price,t.rating desc")
						.setParameter("color", color).setParameter("size", size).setParameter("gender", gender)
						.getResultList()){
							matchedShirts.add((TShirtDetails)oneObject);
						}						
		}
		
		return matchedShirts;
	}
}
