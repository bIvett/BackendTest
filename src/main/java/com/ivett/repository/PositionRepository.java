package com.ivett.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ivett.domain.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {
	
	List<Position> findAll();

	List<Position> findAllByNameIgnoreCase(String name);

	List<Position> findAllUrlByNameContainsIgnoreCaseAndLocationContainsIgnoreCase(String name, String location);

	List<Position> findAllByNameContainsIgnoreCaseAndLocationContainsIgnoreCase(String name, String location);
	
}