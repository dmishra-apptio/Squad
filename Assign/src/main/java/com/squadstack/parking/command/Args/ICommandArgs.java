package com.squadstack.parking.command.Args;

import com.squadstack.parking.enums.ParkingLotEnums;

public interface ICommandArgs {

    public ParkingLotEnums.ArgsType getArgsForType();
}
