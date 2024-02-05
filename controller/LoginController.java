package com.nagarro.advancedJava.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.advancedJava.service.StoreCSVData;
import com.nagarro.advancedJava.service.ValidateUser;

@Controller
public class LoginController {
	@Autowired
	ValidateUser validateUser;
	@Autowired
	StoreCSVData storeCSVData;
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) throws IllegalStateException, IOException {
		
		boolean isValid = this.validateUser.validate(username, password);
		
		ModelAndView mv = new ModelAndView();
		
		if(isValid) {
			
			// save CSV file data into database
			this.storeCSVData.saveProducts();
			
			HttpSession session = request.getSession();
			session.setAttribute("username", username);			
			mv.setViewName("search");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("message", "invalid/wrong email or password");
			mv.setViewName("index");			
		}
		
		return mv;
	}
}
