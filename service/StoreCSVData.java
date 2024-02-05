package com.nagarro.advancedJava.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.advancedJava.dao.TShirtDao;
import com.nagarro.advancedJava.domain.TShirtDetails;

@Component
public class StoreCSVData {
	
	@Autowired
	TShirtDao tShirtDao;
	
	public void saveProducts() throws IllegalStateException, IOException {
		String folderPath = "C:\\NAGARRO credentials\\AdvanceJava4\\Spring-MVC-project-main (1)\\Spring-MVC-project-main (1)\\Spring-MVC-project-main\\Assignment-8\\src\\main\\resources";
		
		saveData(folderPath);
	}
	
	// function that saves data in database
	public void saveData(String path) throws IllegalStateException, IOException{
		
		// Path of all CSV files
		String[] pathList = getAllFilePath(path);
		
		final String delimiter = ",";
		for(int i=0 ; i<pathList.length;i++) {
			try {
		         File file = new File(pathList[i]);
		         FileReader fr = new FileReader(file);
		         BufferedReader br = new BufferedReader(fr);
		         String line = "";
		         String[] tempArr;
		         while((line = br.readLine()) != null) {
		            tempArr = line.split(delimiter);

		            try {
		            	TShirtDetails shirt = new TShirtDetails();
		            	shirt.setId(tempArr[0]);
		            	shirt.setName(tempArr[1]);
		            	shirt.setColor(tempArr[2]);
		            	shirt.setGender(tempArr[3]);
		            	shirt.setSize(tempArr[4]);
		            	shirt.setPrice(Double.parseDouble(tempArr[5]));
		            	shirt.setRating(Double.parseDouble(tempArr[6]));
		            	shirt.setAvailability(tempArr[7]);
		            	
		            	this.tShirtDao.save(shirt);
		            }catch(Exception e){
		            	continue;
		            }
		         }
		         br.close();
		         } catch(Exception e) {
		            System.out.println(e);
		         }
		}		
	}
	
	// function that retrieves CSV files path
	public String[] getAllFilePath(String path) {
		
		// Creating a File object for directory
		File directoryPath = new File(path);
		
		// List of all files and directories
	    File filesList[] = directoryPath.listFiles();
	    
	    // String Array that stores the CSV files path
	    String [] pathList = new String[filesList.length];
	    int i=0;
	    for(File file : filesList) {
	    	pathList[i++] = file.getAbsolutePath();
	    }
	    
	    return pathList;
	}
}






























