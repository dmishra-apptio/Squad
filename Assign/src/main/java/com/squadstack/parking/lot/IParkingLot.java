package com.squadstack.parking.lot;

import com.squadstack.parking.command.Args.ICommandArgs;
import com.squadstack.parking.exceptions.SlotAlreadyAvailableException;
import com.squadstack.parking.model.Slot;
import com.squadstack.parking.model.Vehicle;

import java.util.List;

public interface IParkingLot<T extends ICommandArgs> {

    Integer parkVehicle(T commandArgs);
    Vehicle unParkVehicle(T commandArgs) throws SlotAlreadyAvailableException;
    void createParkingSlot(T commandArgs);
    List<Integer> getAllVehicleRegNo(T commandArgs);
    Slot getSlotNo(T commandArgs);
    String getSlotsNo(T commandArgs);

}
