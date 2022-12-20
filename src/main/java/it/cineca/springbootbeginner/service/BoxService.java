package it.cineca.springbootbeginner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import it.cineca.springbootbeginner.dto.BoxDto;
import it.cineca.springbootbeginner.models.Box;
import io.micrometer.common.util.StringUtils;
import it.cineca.springbootbeginner.repository.BoxRepository;
import it.cineca.springbootbeginner.wrapper.BoxWrapper;

@Service
public class BoxService {
	
	@Autowired
	private BoxRepository boxRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired 
	private BoxWrapper boxWrapper;
	
	public List<Box> getByNamePaged(String name, PageRequest page){
		return StringUtils.isBlank(name)?
				boxRepo.findAll(page).toList():
					boxRepo.findByName(name, page);
	}
	
	public Optional<Box> getOne(Long id) {
		return boxRepo.findById(id);
	}
	
	public Box createBox(BoxDto dto) {
		Box daCreare=modelMapper.map(dto, Box.class);
		boxWrapper.getListaBox().add(dto);
		return daCreare;
		
	}
	
	public Optional<Box> updateBox(Long id, BoxDto dto){
		Box daCreare=null;
		if (boxRepo.findById(id).isPresent())
			daCreare=modelMapper.map(dto, Box.class);
		return Optional.ofNullable(daCreare);
	}
	
	public void deleteBox(Long id) {
		boxRepo.deleteById(id);
	}
	
	public void submit() {
		List<BoxDto> wrappedBox = boxWrapper.getListaBox();
		
		if(wrappedBox.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED);
		}
		
		for(BoxDto boxDto : wrappedBox) {
			Box box = modelMapper.map(boxDto, Box.class);
			boxRepo.save(box);
		}
		
		boxWrapper.setListaBox(new ArrayList<>());
	}
	
	public List<Box> getWrapped(){
		List<Box> ret = new ArrayList<>();
		boxWrapper.getListaBox().stream().forEach(b->ret.add(modelMapper.map(b, Box.class)));
		return ret;
	}

}
