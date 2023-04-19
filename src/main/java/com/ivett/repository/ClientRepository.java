package com.ivett.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ivett.domain.Client;

import jakarta.validation.Valid;

public interface ClientRepository extends CrudRepository<Client, Long> {
	
	List<Client> findAll();

	Client findByEmail(String email);

	Client findByActivation(String code);
	
}