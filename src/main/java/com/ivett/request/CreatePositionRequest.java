package com.ivett.request;

import com.ivett.domain.Client;
import com.ivett.domain.Position;

import jakarta.validation.Valid;

public class CreatePositionRequest {

	    @Valid
	    private Client client;

	    @Valid
	    private Position position;

	    public Client getClient() {
	        return client;
	    }

	    public void setClient(Client client) {
	        this.client = client;
	    }

	    public Position getPosition() {
	        return position;
	    }

	    public void setPosition(Position position) {
	        this.position = position;
	    }
	}