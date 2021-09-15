package com.parkinglot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SlotTest {
    @Test
    public void checkSlot(){
        Slot slot = new Slot(1);
        int slotNumber=slot.getSlotNumber();
        assertEquals(slotNumber,1);
        assertTrue(slot.isSlotFree());
    }
}