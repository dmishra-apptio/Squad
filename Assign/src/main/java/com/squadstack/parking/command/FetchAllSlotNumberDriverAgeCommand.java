package com.squadstack.parking.command;

import com.squadstack.parking.command.Args.FetchAllSlotNumberDriverAgeArgs;
import com.squadstack.parking.lot.IParkingLot;

import java.util.List;

public class FetchAllSlotNumberDriverAgeCommand<T extends FetchAllSlotNumberDriverAgeArgs> extends Command {

    private T commandArgs;

    public FetchAllSlotNumberDriverAgeCommand(IParkingLot parkingLot, T commandArgs) {
        super(parkingLot);
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        String res = parkingLot.getSlotsNo(commandArgs);
        return res;
    }

    @Override
    public String description() {
        return String.format("Return all Slot Number of all car which"
            + "have driver of % age", this.commandArgs.getDriverAge());
    }

}
