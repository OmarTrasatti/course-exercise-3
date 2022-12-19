package it.cineca.springbootbeginner;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.cineca.springbootbeginner.model.Box;
import it.cineca.springbootbeginner.service.BoxService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		  		classes = SpringBeginnerDay4Application.class)
@AutoConfigureMockMvc
class SpringBeginnerDay4ApplicationTests {
	
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BoxService boxService;
    	
//	@Test
//	public void getBoxByIdOk() {
//		try {
//			mvc.perform(get("/box"))
//			  .andExpect(status().isOk());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void getBoxByIdOk() {
		try {
			Box newBox = new Box(1L, "HeartBox", "Jeinny", null);
			when(boxService.getBoxById(1L)).thenReturn(newBox);
			
			final String expectedResponse = objectMapper.writeValueAsString(newBox);
			
			mvc.perform(get("/box/1"))
			  .andExpect(status().isOk())
			  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			  .andExpect(content().json(expectedResponse));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getBoxByIdNotFound() {
		try {
			when(boxService.getBoxById(56L)).thenReturn(null);
		
			mvc.perform(get("/box/56"))
			  .andExpect(status().isNotFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getBoxByIdBadRequest() {
		try {
		mvc.perform(get("/box/HOLA"))
			  .andExpect(status().isBadRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
