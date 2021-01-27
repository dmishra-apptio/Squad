package com.squadstack.parking.command;

import com.squadstack.parking.command.Args.LeaveSlotArgs;
import com.squadstack.parking.exceptions.SlotAlreadyAvailableException;
import com.squadstack.parking.lot.IParkingLot;
import com.squadstack.parking.model.Vehicle;

public class LeaveSlotCommand<T extends LeaveSlotArgs> extends Command {

    private T commandArgs;

    public LeaveSlotCommand(IParkingLot parkingLot, T commandArgs) {

        super(parkingLot);
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() throws SlotAlreadyAvailableException {
      Vehicle vehicle = parkingLot.unParkVehicle(commandArgs);
      return String.format(
          response(), commandArgs.getSlotNumber(), vehicle.getRegNo(),
          vehicle.getDriverAge());
    }

    @Override
    public String description() {
        return  String.format("Vacant slot number %s",
            commandArgs.getSlotNumber());
    }

    @Override
    public String response() {
        return "slot number %s vacated, the car with vehicle"
            + " registration number %s left the space, the driver of the car "
            + "was of age %s";
    }
}
