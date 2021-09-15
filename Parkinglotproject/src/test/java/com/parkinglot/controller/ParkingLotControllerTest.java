package com.parkinglot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkinglot.model.Car;
import com.parkinglot.model.Slot;
import com.parkinglot.model.Token;
import com.parkinglot.service.ParkingLot;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringJUnit4ClassRunner.class)

public class ParkingLotControllerTest {

	ObjectMapper mapper = new ObjectMapper();

	@MockBean
	private ParkingLotController parkingLotController;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private ParkingLot parkingLot;
	@Before
	public void setup() {

		MockitoAnnotations.openMocks(this.parkingLotController);

		this.mockMvc = MockMvcBuilders.standaloneSetup(parkingLotController).build();

	}

	@Test
	public void getCarByColor() throws Exception {
		Token token = new Token("ASDFGHJ", new Slot(1), new Car("Blue", "123"));
//		String result = "Token Number: " + token.getTokenNumber() + "\nSlot Number: " + token.getSlotDetails().getSlotNumber() + "\nCar reg no: " + token.getCarDetails().getRegistrationNumber();
		when(parkingLot.searchCarColor(anyString())).thenReturn(token);
		mockMvc.perform(get("/searchCarbycolor/{carColors}",""))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$['carDetails'].color").value("Blue"))
				.andExpect(status().isOk());

	}

	@Test
	public void getCarBynum() throws Exception {
		//mock the data return by the service class
		Token token = new Token("ASDFGHJ", new Slot(2), new Car("Blue", "123"));
//		String result = "Token Number: " + token.getTokenNumber() + "\nSlot Number: " + token.getSlotDetails().getSlotNumber() + "\nCar reg no: " + token.getCarDetails().getColor();
		when(parkingLot.searchCarColor("Blue")).thenReturn(token);
		mockMvc.perform(get("/searchCarbycolor/{carNum}","123"))
				.andExpect(MockMvcResultMatchers.jsonPath("$['carDetails'].registrationNumber").value("123"))
				.andExpect(status().isOk());

	}

	@Test
	public void UnParktheCarTest() throws Exception {
		String responseString = "Car entry removed";
		when(parkingLot.unParkTheCar("123123")).thenReturn(responseString);
		mockMvc.perform(delete("/unParkTheCar/{tokenNumber}", "123123"))
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) jsonPath("$", is("Car entry removed")));
			verify(parkingLot, times(1)).unParkTheCar("123123");
			verifyNoMoreInteractions(parkingLot);


	}


	public void testTheParkTheCarService() throws Exception {
		Token token = new Token("XYZ123123", new Slot(123), new Car("Blue", "123"));
		when(parkingLot.parkCar("Blue","123")).thenReturn(token);
		mockMvc.perform(post("/ParkTheCar")
						.param("Color", "Blue")
						.param("Number", "123")
				)
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) jsonPath("$['carDetails'].carColor").value("Blue"))
				.andExpect((ResultMatcher) jsonPath("$['carDetails'].carColor").value("Blue"))
				.andExpect((ResultMatcher) jsonPath("$.tokenNumber").value("XYZ123123"));
		verify(parkingLot, times(1)).parkCar("Blue", "123");
		verifyNoMoreInteractions(parkingLot);

	}
}
	


