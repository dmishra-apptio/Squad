package com.squadstack.parking.command;

import com.squadstack.parking.command.Args.FetchAllVehicleRegNoForDriverAgeArgs;
import com.squadstack.parking.command.Args.FetchSlotNumberCarRegQueryArgs;
import com.squadstack.parking.command.Args.ICommandArgs;
import com.squadstack.parking.lot.IParkingLot;
import com.squadstack.parking.model.Slot;

import java.util.Objects;
import java.util.Optional;

public class FetchSlotNumberCarRegQueryCommand<T extends FetchSlotNumberCarRegQueryArgs> extends Command {

    private T commandArgs;

    public FetchSlotNumberCarRegQueryCommand(IParkingLot parkingLot, T commandArgs) {
        super(parkingLot);
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        Slot slot = parkingLot.getSlotNo(commandArgs);
        Integer res = Objects.nonNull(slot) ? slot.getNumber():null;
        return String.format(response(),commandArgs.getRegNo(), res);
    }

    @Override
    public String description() {
        return String.format("Return slot number for the car with "
            + "registration number %s", commandArgs.getRegNo());
    }

    @Override
    public String response() {
        return "Car with vehicle registration number %s "
            + " has been parked at slot number %s";
    }
}

