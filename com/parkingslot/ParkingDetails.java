package com.parkingslot;

public class ParkingDetails {

    int slotNumber;
    String carRegistrationNumber;
    String carColor;

    public ParkingDetails(int slotNumber, String carRegistrationNumber, String carColor) {
        this.slotNumber = slotNumber;
        this.carRegistrationNumber = carRegistrationNumber;
        this.carColor = carColor;
    }

    public ParkingDetails() {

    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

}
