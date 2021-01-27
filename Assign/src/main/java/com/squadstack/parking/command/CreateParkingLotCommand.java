package com.squadstack.parking.command;

import com.squadstack.parking.command.Args.CreateParkingLotArgs;
import com.squadstack.parking.command.Args.ICommandArgs;
import com.squadstack.parking.lot.IParkingLot;


public class CreateParkingLotCommand<T extends CreateParkingLotArgs> extends Command {

    T commandArgs;

    public CreateParkingLotCommand(IParkingLot parkingLot, T commandArgs) {
        super(parkingLot);
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        this.parkingLot.createParkingSlot(commandArgs);
        return response();
    }

    @Override
    public String description() {
        return String.format("Create a parking lot of %s slots",
            this.commandArgs.getSlotSize());
    }

    public String response(){
        return String.format("Created Parking of %s slots",
            this.commandArgs.getSlotSize());
    }
}
