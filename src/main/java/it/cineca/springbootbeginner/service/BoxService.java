package it.cineca.springbootbeginner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.cineca.springbootbeginner.model.Box;
import it.cineca.springbootbeginner.repository.BoxRepository;

@Service
public class BoxService {
	
	@Autowired
	private BoxRepository boxRepository;
	
	public Box getBoxById(Long id) {
		return boxRepository.findById(id).orElse(null);
	}
}
