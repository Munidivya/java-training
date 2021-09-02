package com.parkingslot;

import java.util.HashMap;
import java.util.Scanner;

public class ParkingSlot {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int totalSlots;
        System.out.println("Total number of slots: ");
        totalSlots = input.nextInt();
        HashMap<Integer, ParkingDetails> totalParkingDetails = new HashMap<Integer, ParkingDetails>();
        ParkingSlotUtil parkingSlotUtil = new ParkingSlotUtil();
        while (true) {
            System.out.println("************  Select the option: *******");
            System.out.print("1. Allocate slot");
            System.out.print("2. Remove car slot");
            System.out.print("3. Show all parking Details");
            System.out.print("4. slot number using registration number");
            System.out.print("5. slot numbers of car using color \n ");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    if(totalParkingDetails.size() == totalSlots) {
                        System.out.println("***********  slots are full ********");
                    }
                    else {
                        System.out.println("\n ****** Enter car registration number: ***** \n");
                        String registratoinNumber =  input.next();
                        System.out.println("\n ************ Enter car color: ********** \n");
                        String carColor = input.next();

                        totalParkingDetails = parkingSlotUtil.allocateSlot(totalParkingDetails, totalSlots,carColor,registratoinNumber);
                        System.out.println("*** slot added completed ****");
                    }
                    break;
                case 2:
                    if(totalParkingDetails.size() == 0) {
                        System.out.println("slots are empty");
                    }
                    else {
                        System.out.println("\n ****** Enter slot number to be remove: ****** \n");
                        int slotNumber = input.nextInt();
                        totalParkingDetails = parkingSlotUtil.removeCarSlot(totalParkingDetails,slotNumber);
                        System.out.println("*** slot removed completed ****");
                    }
                    break;
                case 3:
                    parkingSlotUtil.showAllParkingSlots(totalParkingDetails);
                    break;
                case 4:
                    parkingSlotUtil.showSlotNumberUsingRegistartionNumber(totalParkingDetails);
                    break;
                case 5:
                    parkingSlotUtil.showSlotNumbersUsingColor(totalParkingDetails);
                    break;
            }
        }
    }




}
