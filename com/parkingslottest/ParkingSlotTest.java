package com.parkingslottest;

import com.parkingslot.ParkingDetails;
import com.parkingslot.ParkingSlotUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ParkingSlotTest {
    @Test
    public void checkingParkingDetailsTest(){
        HashMap<Integer, ParkingDetails> totalParkingDetails = new HashMap<Integer, ParkingDetails>();
        totalParkingDetails.put(1,new ParkingDetails(1,"1234","red"));
        totalParkingDetails.put(2,new ParkingDetails(2,"1233","blue"));
        ParkingSlotUtil parkingSlotUtil=new ParkingSlotUtil();
        totalParkingDetails = parkingSlotUtil.allocateSlot(totalParkingDetails,10,"green","37382");
        Assert.assertEquals(3,totalParkingDetails.size());

    }
    @Test
    public void checkingCarColorTest(){
        HashMap<Integer, ParkingDetails> totalParkingDetails = new HashMap<Integer, ParkingDetails>();
        ParkingSlotUtil parkingSlotUtil=new ParkingSlotUtil();
        //after allocate
        totalParkingDetails = parkingSlotUtil.allocateSlot(totalParkingDetails,10,"green","37382");
        Assert.assertNotSame("red",totalParkingDetails.get(1).getCarColor());
    }
    @Test
    public void checkRegistrationNumberTest(){
        HashMap<Integer, ParkingDetails> totalParkingDetails = new HashMap<Integer, ParkingDetails>();
        ParkingSlotUtil parkingSlotUtil=new ParkingSlotUtil();
        totalParkingDetails = parkingSlotUtil.allocateSlot(totalParkingDetails,10,"green","37382");
        Assert.assertSame("37382",totalParkingDetails.get(1).getCarRegistrationNumber());
    }


}
