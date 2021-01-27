package com.squadstack.parking.slotops;

import com.squadstack.parking.enums.ParkingLotEnums.SlotState;
import com.squadstack.parking.model.Slot;
import com.squadstack.parking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class SlotCollectionImpl implements ISlotCollection{

    private final List<Slot> slots;

    public SlotCollectionImpl() {
        this.slots = new ArrayList<Slot>();
    }

    @Override
    public void addSlot(Slot slot) {
       this.slots.add(slot);
    }

    @Override
    public void removeSlot(Slot slot) {

    }

    @Override
    public void updateSlot(Slot slot, SlotState slotState, Vehicle vehicle) {
        slot.setSlotState(slotState);
        slot.setVehicle(vehicle);
    }

    @Override
    public Slot getSlot(Integer slotNumber) {
        return this.slots.get(slotNumber - 1);
    }

    @Override
    public List<Slot> getAllSlot() {
        return this.slots;
    }
}
