package com.parkinglot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parkinglot.model.Car;
import com.parkinglot.model.Token;
import com.parkinglot.service.ParkingLot;


@RestController

public class ParkingLotController {
//ds
	ParkingLot parkinglot = new ParkingLot(5);

	@PostMapping(path = "ParkCar",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> parkCar(@RequestBody Car car)
		{
			return new ResponseEntity<>(parkinglot.parkCar(car.getColor(),car.getRegistrationNumber()),HttpStatus.OK);
		}


	@DeleteMapping("/unParkTheCar")
	String unParkCa(@RequestParam(value = "token")  String token ) {
		String unparkcar=parkinglot.unParkTheCar(token);
		System.out.println(unparkcar);
		return unparkcar;

	}

	@GetMapping("/searchCarbycolor/{carColors}")
	public Token searchCarByColor(@PathVariable String carColors) {
		Token token = parkinglot.searchCarColor(carColors);
		return token;
	}
	@GetMapping("/searchCarbynum/{carNumber}")
	public Token searchCarByNum(@PathVariable String carNumber) {
		Token token = parkinglot.searchCarNumber(carNumber);
		return token;
	}

	@GetMapping("/DisplayAllParkedCarDetails")
	public List<Token> DisplayAllParkedCarDetails() {
		return parkinglot.showListOfCarDetails();
	}
}
