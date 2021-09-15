package com.parkinglot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parkinglot.model.*;
@Service
public class ParkingLot {
    private int totalNumberOfSlots;
    ArrayList<Slot> availableSlotList;
    private List<Token> tokenForLot;
    private List<Token> historyOfParking;
    private ArrayList<Slot> totalSlots;

    public ParkingLot(int totalNumberOfSlots){
        this.totalNumberOfSlots = totalNumberOfSlots;
        this.tokenForLot = new ArrayList<>();
        this.historyOfParking = new ArrayList<>();

        this.availableSlotList = initializeSlot(totalNumberOfSlots);
    }

    public ParkingLot() {

    }

    public ArrayList<Slot> initializeSlot(int totalNumberOfSlot){

        this.totalSlots = new ArrayList<Slot>() {};
        for (int i=1; i<= totalNumberOfSlot; i++) {
            Slot getSlotAssignment = new Slot(i);
            totalSlots.add(getSlotAssignment);
        }
        return totalSlots;

    }
    public Token parkCar(String carColor, String carNumber){
        Car car = new Car(carColor,carNumber);
        if(isSlotAvailable()){
            Slot availableSlot = getTheNextFreeSlot();
            Token parkingToken = new Token(String.valueOf(System.currentTimeMillis()),availableSlot,car);
            this.tokenForLot.add(parkingToken);
            return parkingToken;
        }else {
            return null;
        }
    }

    public String unParkTheCar(String token){
        for(Token tokenInLot:tokenForLot){
            if(tokenInLot.getTokenNumber().equals(token)){
                tokenForLot.remove(tokenInLot);
                Slot slot = tokenInLot.getSlotDetails();
                int slotNumber = slot.getSlotNumber();
                return removeCarFromSlot(tokenInLot,slotNumber);
            }
            return "No token found";
        }
        return null;
    }

    private String removeCarFromSlot(Token token, int slotNumber) {
        for (Slot removeEntry:availableSlotList){

            if(removeEntry.getSlotNumber() == slotNumber){
                removeEntry.makeSlotFree();
                Token historyToken = token.updateCheckOutTime();
                historyOfParking.add(historyToken);
                return "Car entry removed";
            }

        }
        return null;
    }

    private Slot getTheNextFreeSlot() {
        for(Slot slot : availableSlotList){
            if(slot.isSlotFree()){
                slot.makeSlotOccupied();
                return slot;
            }
        }
        return null;
    }
    public Token searchCarNumber(String carNumber){
        for(Token tokenSearch:tokenForLot){
            String carDetails = tokenSearch.getCarDetails().getRegistrationNumber();
            if(carDetails.equalsIgnoreCase(carNumber)){
                return tokenSearch;
            }
        }
        return null;
    }
    public Token searchCarColor(String colour){
        for(Token tokenSearch:tokenForLot){
            String carDetails = tokenSearch.getCarDetails().getColor();
            if(carDetails.equalsIgnoreCase(colour)){
                return tokenSearch;
            }
        }
        return null;
    }
//   public List<Token> searchCarColor() {
//       for (Token tokenSearch : tokenForLot) {
//           System.out.println("Token Number: " + tokenSearch.getTokenNumber());
//           System.out.println("Slot Number: " + tokenSearch.getSlotDetails().getSlotNumber());
//           System.out.println("Car Number: " + tokenSearch.getCarDetails().getRegistrationNumber());
//       }
//       return tokenForLot;
//   }
    private boolean isSlotAvailable() {
        boolean isSlotAvailable = false;

        for(Slot slot:availableSlotList){
            if(slot.isSlotFree()){
                isSlotAvailable = true;
                break;
            }
        }
        return isSlotAvailable;
    }
    public String listAllCars(){
        for(Token tokenSearch:tokenForLot){
            String carDetails ="\nToken Number: " +tokenSearch.getTokenNumber()+"\nSlot Number: " +tokenSearch.getSlotDetails().getSlotNumber()+"\nCar Color: " +tokenSearch.getCarDetails().getColor();
            return "\nToken Number: " +tokenSearch.getTokenNumber()+"\nSlot Number: " +tokenSearch.getSlotDetails().getSlotNumber()+"\nCar Color: " +tokenSearch.getCarDetails().getColor();
        }
        return "No cars parked";
    }

    public List<Token>  showListOfCarDetails() {
        for (Token i : tokenForLot) {
            System.out.println("Token Number: " + i.getTokenNumber());
            System.out.println("Slot Number: " + i.getSlotDetails().getSlotNumber());
            System.out.println("car color: " + i.getCarDetails().getColor());
            System.out.println("Car Number: " + i.getCarDetails().getRegistrationNumber());
        }
        return tokenForLot;

    }
//    public List <Token>  searchCarNumber() {
//        for (Token tokenSearch : tokenForLot) {
//            System.out.println("Token Number: " + tokenSearch.getTokenNumber());
//            System.out.println("Slot Number: " + tokenSearch.getSlotDetails().getSlotNumber());
//            System.out.println("Car Number: " + tokenSearch.getCarDetails().getColor());
//        }
//        return tokenForLot;
//    }

}