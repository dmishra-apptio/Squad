package com.squadstack.parking.slotops;

import com.squadstack.parking.enums.ParkingLotEnums.SlotState;
import com.squadstack.parking.model.Slot;
import com.squadstack.parking.model.Vehicle;

import java.util.List;

public interface ISlotCollection {

    void addSlot(Slot slot);
    void removeSlot(Slot slot);
    void updateSlot(Slot slot, SlotState state, Vehicle vehicle);
    Slot getSlot(Integer slotNumber);
    List<Slot> getAllSlot();
}
