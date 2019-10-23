package com.berserk.animeinitservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.berserk.animeinitservice.controller.ServiceController;
import com.berserk.animeinitservice.error.RestExceptionHandler;
import com.berserk.animeinitservice.model.Anime;
import com.berserk.animeinitservice.service.AnimeService;
import com.berserk.animeinitservice.util.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ServiceControllerTest {
	
	private MockMvc mockMvc;
	private List<Anime> myAnimes;
	
	@InjectMocks
	private ServiceController serviceController;
	
	@Mock
	private AnimeService animeService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		serviceController = new ServiceController(animeService, new RestTemplate());
		mockMvc = MockMvcBuilders
                .standaloneSetup(serviceController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
		myAnimes = new ArrayList<>(
				Arrays.asList(new Anime(
						1L, 
						null, 
						"TV", 
						"One piece", 
						null, 
						null, 
						null, 
						958, 
						new ArrayList<>())));
		Mockito.reset(animeService);
	}

	@Test
	public void givenJson_whenProcessJson_thenExpectOkAndSizeEquals1AndTitleEqualsOnePiece() throws Exception {
		final String RESOURCE_LOCATION = URL.SAVE_EXTERNAL;
		Mockito.when(animeService.processJson(anyString())).thenReturn(myAnimes);

		mockMvc
		.perform(
				get(RESOURCE_LOCATION)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].title", is("One piece")));
	}
	
	@Test
	public void givenBadFormatJson_whenProcessJson_thenExpectException() throws Exception {
		final String RESOURCE_LOCATION = URL.SAVE_EXTERNAL;
		Mockito.when(animeService.processJson(anyString())).thenThrow(
				new IOException("Cannot deserialize json"));

		mockMvc
		.perform(
				get(RESOURCE_LOCATION)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isInternalServerError());
	}
}