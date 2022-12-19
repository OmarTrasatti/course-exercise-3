package it.cineca.springbootbeginner.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.cineca.springbootbeginner.model.Box;

public interface BoxRepository extends CrudRepository<Box, Long> {
	
	public Optional<Box> findById(Long id);
}
