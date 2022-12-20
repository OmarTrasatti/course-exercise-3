package it.cineca.springbootbeginner.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.cineca.springbootbeginner.dto.BoxDto;
import it.cineca.springbootbeginner.models.Box;
import it.cineca.springbootbeginner.service.BoxService;
import jakarta.validation.Valid;

@RestController
public class BoxController {
	@Autowired
	private BoxService boxService;
	
	@GetMapping("/box")
	public ResponseEntity<List<Box>> getBoxes(			
			@RequestParam(name = "page", required = true) int page, 
			@RequestParam(name = "pagesize", required = false, defaultValue = "3") int pagesize,
			@RequestParam(name = "name", required = false) String name
			){
		PageRequest pageReq = PageRequest.of(page, pagesize);
		List<Box> boxs = this.boxService.getByNamePaged(name, pageReq);	
		return ResponseEntity.ok(boxs);
	}
	
	@GetMapping("box/{id}")
	public ResponseEntity<Box> getBox(			
			@PathVariable("id") Long id
			){	
		return ResponseEntity.of(this.boxService.getOne(id));
	}
	
	@PostMapping("/box")
	public ResponseEntity<Box> createBox( @RequestBody @Valid BoxDto dto){
		return ResponseEntity.ok(boxService.createBox(dto));
	}
	
	@PatchMapping("/box/{id}")
	public ResponseEntity<Box> updateBox(@PathVariable("id") Long id, @RequestBody @Valid BoxDto body) {
		Optional<Box> updatedBox = this.boxService.updateBox(id, body);
		return ResponseEntity.of(updatedBox);
	}
	
	@DeleteMapping("/box/{id}")
	public ResponseEntity<Boolean> deleteBox(@PathVariable("id") Long id) {
		this.boxService.deleteBox(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/submit")
	public void submitBoxes() {
		this.boxService.submit();
	}
	
	@GetMapping("/wrapped")
	public ResponseEntity<List<Box>> getWrapped(){
		return ResponseEntity.ok(this.boxService.getWrapped());
	}	
}
