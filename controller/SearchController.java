package com.nagarro.advancedJava.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.advancedJava.domain.TShirtDetails;
import com.nagarro.advancedJava.service.FilterData;

@Controller
public class SearchController {
	
	static List<TShirtDetails> list = new ArrayList<TShirtDetails>() ;
	
	@Autowired
	FilterData filterData;

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam("color") String color1, @RequestParam("size") String size1, 
								@RequestParam("gender") String gender1, @RequestParam("preference") String preference,HttpServletRequest request) throws IllegalStateException, IOException{
		
		ModelAndView mv = new ModelAndView();
		
		// setting the correct format
		color1=getColor(color1);
		size1=getSize(size1);
		gender1=getGender(gender1);
		preference=getPreference(preference);		
		
		
//		if(color1!="Black" || color1!="Pink" || color1!="White" || color1!="Yellow" || color1!="Grey" || color1!="Maroon" || color1!="Blue" || color1!="Green") {
//			HttpSession session1 = request.getSession();
//			session1.setAttribute("message2","INVALID COLOR" );	
//			mv.setViewName("search");
//			
//		}
//		
//		
//		
		if(!color1.equals("Black") && !color1.equals("Pink") && !color1.equals("White") && !color1.equals("Yellow") && !color1.equals("Grey") && !color1.equals("Maroon") && !color1.equals("Blue") && !color1.equals("Green")) {
//			HttpSession session1 = request.getSession();
//		    session1.setAttribute("message2","INVALID COLOR" );	
			mv.addObject("message2","INVALID color");
			mv.setViewName("search");
			
		}
		
		
		if(!size1.equals("M") && !size1.equals("S") && !size1.equals("XL") && !size1.equals("L")) {
//			HttpSession session2 = request.getSession();
//			session2.setAttribute("message3","INVALID SIZE" );	
			mv.addObject("message3","INVALID size");
			mv.setViewName("search");
			
		}
		
		
		if(!gender1.equals("M") && !gender1.equals("F")) {
//			HttpSession session3 = request.getSession();
//			session3.setAttribute("message4","INVALID GENDER" );
			mv.addObject("message4","INVALID gender");
			mv.setViewName("search");
			
		}
		// filer the T-Shirt products and storing them in a list
		list =  this.filterData.filter(color1, size1, gender1, preference) ;
		
		 
		String result= "Sorry!!! No Items Found.";
		mv.addObject("notFound", result);
		mv.setViewName("search");
		return mv;									
	}
	
	// returns the list of matched products
	public static List<TShirtDetails> getList() {
		return list;
	}
	
	// returns correct format of color
	public String getColor(String color) {
		String first =  color.substring(0,1);
		String last =   color.substring(1);
		color =  first.toUpperCase()+ last.toLowerCase();
		return color;
	}
	// returns correct format of size
	private String getSize(String size) {
		size=size.toUpperCase();
		return size;
	}
	// returns correct format of gender
	private String getGender(String gender) {
		gender=gender.toUpperCase();
		return gender;
	}
	// returns correct format of preference
	private String getPreference(String preference) {
		String first =  preference.substring(0,1);
		String last =   preference.substring(1);
		preference =  first.toUpperCase()+ last.toLowerCase();
		return preference;
	}
}
