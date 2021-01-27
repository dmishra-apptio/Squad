package com.squadstack.parking.command;

import com.squadstack.parking.command.Args.DriverAgeArgs;
import com.squadstack.parking.command.Args.ICommandArgs;
import com.squadstack.parking.command.Args.ParkArgs;
import com.squadstack.parking.lot.IParkingLot;

public class ParkCommand<T extends ParkArgs> extends Command {

    private T commandArgs;

    public ParkCommand(IParkingLot parkingLot, T commandArgs) {
        super(parkingLot);
        this.commandArgs = commandArgs;
    }

    public ParkCommand() {
        this(null, null);
    }

    @Override
    public String execute() {
        Integer slotNumber = parkingLot.parkVehicle(commandArgs);
       return String.format(
            response(), commandArgs.getVehicleRegNo(), slotNumber);
    }

    @Override
    public String description() {
        DriverAgeArgs driverAgeArgs = (DriverAgeArgs) commandArgs.getDriverAge();
        return String.format("Park car with vehicle registration number %s"
            + " and the car is driven by the driver of age %s", commandArgs.getVehicleRegNo(),
            driverAgeArgs.getDriverAge());
    }

    @Override
    public String response() {
       return "Car with vehicle registration number %s has been parked"
           + " at slot number %s";

    }
}
