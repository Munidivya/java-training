package com.parkingslot;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParkingSlotUtil {
    static Scanner input = new Scanner(System.in);

    public static HashMap<Integer, ParkingDetails> removeCarSlot(HashMap<Integer, ParkingDetails> totalParkingDetails, int slotNumber) {

        boolean found = false;
        if(!found) {
            System.out.println("\n Slot not found \n");
            return totalParkingDetails;

        }
        totalParkingDetails.remove(slotNumber);
        return totalParkingDetails;
    }

    public HashMap<Integer, ParkingDetails> allocateSlot(HashMap<Integer, ParkingDetails> totalParkingDetails, int totalSlots, String carColor, String registratoinNumber) {

        ParkingDetails parkingDetails = new ParkingDetails();
        parkingDetails.setCarColor(carColor);
        parkingDetails.setCarRegistrationNumber(registratoinNumber);
        for(int slotNumber = 1; slotNumber <= totalSlots; slotNumber++) {
            if(!totalParkingDetails.containsKey(slotNumber)) {
                parkingDetails.setSlotNumber(slotNumber);
                totalParkingDetails.put(slotNumber, parkingDetails);
                break;
            }
        }

        return totalParkingDetails;
    }

    public void showAllParkingSlots(HashMap<Integer, ParkingDetails> totalParkingDetails) {
        if(totalParkingDetails.size() == 0) {
            System.out.println("\n *********  slots are empty ********* \n");
            return;
        }
        for (Map.Entry<Integer,ParkingDetails> entry : totalParkingDetails.entrySet()) {
            System.out.println("\n **** car details slot :" + entry.getKey());
            System.out.println("car registration number: " + entry.getValue().getCarRegistrationNumber());
            System.out.println("car color: " + entry.getValue().getCarColor());
        }
    }

    static void showSlotNumberUsingRegistartionNumber(HashMap<Integer, ParkingDetails> totalParkingDetails) {
        if(totalParkingDetails.size() == 0) {
            System.out.println("\n *********  slots are empty ********* \n");
            return;
        }
        System.out.println("\n ********* Enter registration number: *****");
        String registrationNumber = input.nextLine();
        boolean found = false;
        for (Map.Entry<Integer, ParkingDetails> entry : totalParkingDetails.entrySet()) {

            if(entry.getValue().getCarRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                System.out.println("slot number -> " + entry.getValue().getSlotNumber());
                found = true;
            }
        }
        if(!found) {
            System.out.println("\n registration number not found \n");
        }
    }

    public void showSlotNumbersUsingColor(HashMap<Integer, ParkingDetails> totalParkingDetails) {
        if(totalParkingDetails.size() == 0) {
            System.out.println("\n *********  slots are empty ********* \n");
            return;
        }
        System.out.println("\n ************ Enter the color:  ***********");
        String color = input.nextLine();
        boolean found = false;
        for (Map.Entry<Integer, ParkingDetails> entry : totalParkingDetails.entrySet()) {

            if(entry.getValue().getCarColor().equalsIgnoreCase(color)) {
                System.out.println("slot number -> " + entry.getValue().getSlotNumber());
                found = true;
            }
        }

        if(!found) {
            System.out.println("\n cars with given color not found ");
        }
    }
}
