package com.ivett.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivett.domain.Client;
import com.ivett.request.CreatePositionRequest;
import com.ivett.service.ServiceOfJobSearchApp;

import jakarta.validation.Valid;

@RestController
public class HomeController {

	ServiceOfJobSearchApp serviceOfJobSearchApp;
	CreatePositionRequest request;

	@Autowired
	public void setServiceOfJobSearchApp(ServiceOfJobSearchApp serviceOfJobSearchApp) {
		this.serviceOfJobSearchApp = serviceOfJobSearchApp;
	}

	@RequestMapping("/")
	public String index() {
		
		return "";
	}

	@PostMapping("/client")
	private String createClient(@Valid @RequestBody Client client, BindingResult result) {
		
		if (result.hasErrors())
	    	   return result.getFieldError().getDefaultMessage();

		return serviceOfJobSearchApp.registerClient(client);
	}
	
	@GetMapping("/activation/{key}")
	public String activation(
			@PathVariable("key") String key,
			Client client) {
		
		return serviceOfJobSearchApp.clientActivation(key);
		
		}
	
	@PostMapping("/position")
	public String createPosition(@RequestBody CreatePositionRequest request, BindingResult bindingResult) {
		
	    if (bindingResult.hasErrors()) {
	        return bindingResult.getFieldError().getDefaultMessage();
	    }

	    return serviceOfJobSearchApp.createPosition(request);
	}
	
	@GetMapping("/position/search")
	private String searchForPositionUrl(
			@RequestBody CreatePositionRequest request, BindingResult result) {
				
			    if (result.hasErrors())
			        return result.getFieldError().getDefaultMessage();
			    
		return serviceOfJobSearchApp.searchForPositionUrl(request);	
	}
	 
	
	@GetMapping("/position/search/{name}/{location}")
	private String searchForPosition(
			@RequestBody Client client,
			BindingResult bindingResult,
			@Valid @PathVariable ("name") String name,
			@Valid @PathVariable ("location") String location){
		
	    if (bindingResult.hasErrors()) {
	        return bindingResult.getFieldError().getDefaultMessage();
	    }

			return serviceOfJobSearchApp.searchForPosition(client, name, location);

	}
	
}