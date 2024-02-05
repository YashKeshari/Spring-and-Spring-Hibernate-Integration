package com.nagarro.advancedJava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.advancedJava.dao.TShirtDao;
import com.nagarro.advancedJava.domain.TShirtDetails;

@Component
public class FilterData {
	
	@Autowired
	TShirtDao tShirtDao;
	
	public List<TShirtDetails> filter(String color, String size, String gender, String preference){
		List<TShirtDetails> list= new ArrayList<TShirtDetails>();
		
		list = this.tShirtDao.matchingData(color, size, gender, preference);
		
		return list;
	}

}
