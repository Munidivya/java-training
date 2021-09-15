package com.parkinglot.service;

import com.parkinglot.model.Car;
import com.parkinglot.model.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingLotTest {
	@Test
	    public void testParkACar(){
	        ParkingLot parkinglot = new ParkingLot(2);
	        Car car = new Car("Blue","ABC");
	        Token token = parkinglot.parkCar(car.getColor(), car.getRegistrationNumber());
			assertEquals(false, token.getTokenNumber().isBlank());
	    }

	    @Test
	    public void testUnParkACar(){
	        ParkingLot parkinglot = new ParkingLot(2);
	        Car car = new Car("Blue","ABC");

	        Token Token = parkinglot.parkCar(car.getColor(), car.getRegistrationNumber());

	        String unParkMessage = parkinglot.unParkTheCar(Token.getTokenNumber());
	        assertEquals(unParkMessage,"Car entry removed");
	    }

	    @Test
	    public void testToParkFourCarWhenThreeSlotsAvailable(){
	        ParkingLot parkinglot = new ParkingLot(3);
	        Car car1 = new Car("Blue","ABC");
	        parkinglot.parkCar(car1.getColor(), car1.getRegistrationNumber());

	        Car car2 = new Car("Red","ABC2");
	        parkinglot.parkCar(car2.getColor(), car2.getRegistrationNumber());

	       Car car3 = new Car("Yellow","ABC3");
	       parkinglot.parkCar(car3.getColor(), car3.getRegistrationNumber());
	       Car car4 = new Car("Yellow","ABC3");
	       Token Token = parkinglot.parkCar(car4.getColor(), car4.getRegistrationNumber());
	        assertEquals(null, Token);


	    }

	    @Test
	    public void testForColorSearch(){
	        ParkingLot parkinglot = new ParkingLot(2);
	        Car car = new Car("Blue","Ts123");
	        parkinglot.parkCar(car.getColor(), car.getRegistrationNumber());
	        Token searchToken = parkinglot.searchCarColor("Blue");
	        assertNotNull(searchToken);
	    }

	    @Test
	    public void testForCarSearch(){
	        ParkingLot parkinglot = new ParkingLot(2);
	        Car car = new Car("Blue","TS-01-HH-3141");
	        parkinglot.parkCar(car.getColor(), car.getRegistrationNumber());
			Token searchvalue = parkinglot.searchCarNumber("TS-01-HH-3141");
	        assertNotNull(searchvalue);
	    }

	    @Test
	    public void testToListAllCar(){
	        ParkingLot parkinglot = new ParkingLot(2);
	        Car car = new Car("Blue","KA-01-HH-3141");
	        parkinglot.parkCar(car.getColor(), car.getRegistrationNumber());
	        String searchToken = parkinglot.listAllCars();
	        assertNotNull(searchToken);
	    }

	    @Test
	    public void testListEmpty(){
	        ParkingLot parkinglot = new ParkingLot(0);
	        String searchCar = parkinglot.listAllCars();
	        assertEquals(searchCar,"No cars parked");
	    }
}
