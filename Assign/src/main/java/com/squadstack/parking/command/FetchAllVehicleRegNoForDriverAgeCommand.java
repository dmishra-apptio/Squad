package com.squadstack.parking.command;

import com.squadstack.parking.command.Args.FetchAllVehicleRegNoForDriverAgeArgs;
import com.squadstack.parking.lot.IParkingLot;

import java.util.List;

public class FetchAllVehicleRegNoForDriverAgeCommand<T extends FetchAllVehicleRegNoForDriverAgeArgs> extends Command {

    private T commandArgs;

    public FetchAllVehicleRegNoForDriverAgeCommand(IParkingLot parkingLot, T commandArgs) {
        super(parkingLot);
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        List<String> res = parkingLot.getAllVehicleRegNo(commandArgs);
        return res.toString();
    }

    @Override
    public String description() {
        return  String.format("Get all parked vehicle registration number of"
            + " cars parked by the driver of age %s", commandArgs.getDriverAge());
    }

}
