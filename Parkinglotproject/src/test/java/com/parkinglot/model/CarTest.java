package com.parkinglot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {

	@Test
	public void checkwithGivenCarColor() {
		Car c = new Car("White","KA-01-HH-3141");
        assertEquals("White", c.getColor());
	}
	@Test
	public void checkwithGivenCarRegNo() {
		Car c = new Car("White","KA-01-HH-3141");
		assertEquals("KA-01-HH-3141", c.getRegistrationNumber()); 
	}

}
