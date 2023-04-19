package com.ivett.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivett.domain.Client;
import com.ivett.domain.Position;
import com.ivett.repository.ClientRepository;
import com.ivett.repository.PositionRepository;
import com.ivett.request.CreatePositionRequest;

@Service
public class ServiceOfJobSearchApp {
	
	private ClientRepository clientRepository;
	
	private PositionRepository positionRepository;
	
	@Autowired
	public void setClientRepository(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Autowired
	public void setPositionRepository(PositionRepository positionRepository) {
		this.positionRepository = positionRepository;
	}
	
	public String uuidKey(){
		
		return UUID.randomUUID().toString();
    }
	
	public String registerClient(Client client) {
		
		Client clientCheck = clientRepository.findByEmail(client.getEmail());
		
		if (clientCheck != null){
			if (clientCheck.getActivation() != null) {
				return clientCheck.getActivation() + "\n http://localhost:8080/activation/";
			} else {
				return "Existing and activated account";
			}	
		}
	
		client.setEnabled(false);
		client.setActivation(uuidKey());
		clientRepository.save(client);
			
		return client.getActivation() + "\n" + "http://localhost:8080/activation/";
	}
	
	public String clientActivation(String key) {
		
		Client client = clientRepository.findByActivation(key);
		
		if (client == null)
			return "Activation key is incorrect or the account already activated";
	
		client.setEnabled(true);
		client.setActivation(null);
		clientRepository.save(client);
		
		return "Account successfully activated";
	}
	
	public String createPosition(CreatePositionRequest request) {
		
	    Client client = request.getClient();
	    Position position = request.getPosition();
	    
	    Client clientCheck = clientRepository.findByEmail(client.getEmail());
	    

		if (clientCheck == null)
			return "Email does not exist";	

		if (clientCheck.getEnabled() == false)
			return "Inactive account";		
		
		position.setUrl(("http://localhost:8080/position/search/" + position.getName() + "/" + position.getLocation()));
		positionRepository.save(position);
		
		return position.getUrl();
		
	}
	
	public String searchForPosition(Client client, String name, String location) {
		
	    String email = client.getEmail();

	    Client clientCheck = clientRepository.findByEmail(email);

	    if (clientCheck == null)
	    	return "Email does not exist";

	    if (!clientCheck.getEnabled()) 
	    	return "Inactive account";

	    List<Position> positionList = positionRepository.findAllByNameContainsIgnoreCaseAndLocationContainsIgnoreCase(name, location);
	    List<Position> positionResult = new ArrayList<>();

	    for (Position position : positionList) {
	    	positionResult.add(position);
	    }

	    return positionResult.toString().replaceAll("[,\\[\\]]", "");
	    
	}

	public String searchForPositionUrl(CreatePositionRequest request) {
		
		String email = request.getClient().getEmail();
		String name  = request.getPosition().getName();
		String location = request.getPosition().getLocation();
		
	    ArrayList<String> searchResultsUrl = new ArrayList<String>();
	    
	    Client clientCheck = clientRepository.findByEmail(email);
	    
		if (clientCheck == null) 
			return "Email does not exist";	

		if (clientCheck.getEnabled() == false) 
			return "Inactive account";	
	    	
	        List<Position> positionList = positionRepository.findAllByNameContainsIgnoreCaseAndLocationContainsIgnoreCase(name, location);
	        
	        for (Position posisiton : positionList) {
	            searchResultsUrl.add(posisiton.getUrl());
	        }   
	    return String.join("\n", searchResultsUrl);
	}
}	