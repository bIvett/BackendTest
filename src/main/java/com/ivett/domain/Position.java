package com.ivett.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Position {
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Size(max = 50, message = "The length of position must be max 50 characters.")
	private String name;
	@Size(max = 50, message = "The length of location must be max 50 characters.")
	private String location;
	private String url;
	
	
	public Position() {
		
	}
	
	public Position(Long id, @Size(max = 50, message = "The length of position must be max 50 characters.") String name,
			@Size(max = 50, message = "The length of location must be max 50 characters.") String location,
			String url) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public String setUrl(String url) {
		return this.url = url;
	}

	@Override
	public String toString() {
	    return
	    	"\nID: " + id
		    + "\nName: " + name
	        + "\nLocation: " + location
	        + "\nURL: " + url + "\n";
	}
	
}
