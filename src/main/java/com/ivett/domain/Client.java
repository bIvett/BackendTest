package com.ivett.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Client {
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Size(max = 100, message = "The length of name must be max 100 characters.")
	private String name;
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email should be valid.")
	@Email(message = "Email should be valid.")
	private String email;
	private String activation;
	private Boolean enabled;
	
	public Client() {
		
	}
	
	public Client(Long id, @Size(max = 100, message = "The length of name must be max 100 characters.") String name,
			@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email should be valid.") @Email(message = "Email should be valid.") String email,
			String activation, Boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.activation = activation;
		this.enabled = enabled;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getActivation() {
		return activation;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}