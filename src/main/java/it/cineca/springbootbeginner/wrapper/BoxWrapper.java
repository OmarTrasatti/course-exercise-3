package it.cineca.springbootbeginner.wrapper;

import it.cineca.springbootbeginner.dto.BoxDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BoxWrapper {
	List<BoxDto> listaBox=new ArrayList<>();

	public List<BoxDto> getListaBox() {
		return listaBox;
	}

	public void setListaBox(List<BoxDto> listaBox) {
		this.listaBox = listaBox;
	}
	
	public BoxWrapper() {}
	
}
