package com.nagarro.advancedJava.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TShirtDetails {
	
	@Id
	private String id;	
	private String name;	
	private String color;	
	private String gender;	
	private String size;		
	private double price;	
	private double rating;		
	private String availability;
	
	public TShirtDetails(String id, String name, String color, String gender, String size, Double price,
			Double rating, String availability) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.gender = gender;
		this.size = size;
		this.price = price;
		this.rating = rating;
		this.availability = availability;
	}

	public TShirtDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getColor() {
		return color;
	}
	public String getGender() {
		return gender;
	}
	public String getSize() {
		return size;
	}
	public String getAvailability() {
		return availability;
	}
	public double getPrice() {
		return price;
	}
	public double getRating() {
		return rating;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "TShirt [id=" + id + ", name=" + name + ", color=" + color + ", gender=" + gender + ", size=" + size
				+ ", price=" + price + ", rating=" + rating + ", availability=" + availability + "]";
	}
	
}
