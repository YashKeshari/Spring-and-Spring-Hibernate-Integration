package com.nagarro.advancedJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.advancedJava.dao.UserDetailsDao;

@Component
public class ValidateUser {
	
	@Autowired
	UserDetailsDao userDetailsDao;
	
	public boolean validate(String username, String password) {
		
		return this.userDetailsDao.getUser(username, password);
	}
}
