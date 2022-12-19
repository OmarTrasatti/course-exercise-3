package it.cineca.springbootbeginner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.cineca.springbootbeginner.model.Box;
import it.cineca.springbootbeginner.service.BoxService;


@RestController
public class BoxController {
	
	@Autowired
	private BoxService boxService;
	
	private static final String OBJ_MAPPING = "/box";
	
    @RequestMapping(value = OBJ_MAPPING+ "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Box> getBox(@PathVariable Long id) {
    	Box foundBox = boxService.getBoxById(id);
    	if(foundBox == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<Box>(boxService.getBoxById(id), HttpStatus.OK);
    }
}
