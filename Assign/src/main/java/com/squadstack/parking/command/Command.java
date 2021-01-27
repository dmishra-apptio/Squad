package com.squadstack.parking.command;

import com.squadstack.parking.exceptions.SlotAlreadyAvailableException;
import com.squadstack.parking.lot.IParkingLot;


public abstract class Command {

    protected IParkingLot parkingLot;

    public Command(IParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public abstract String execute() throws SlotAlreadyAvailableException;
    public abstract String description();
    public String response(){
        return "";
    }

}
