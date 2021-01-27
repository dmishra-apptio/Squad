package com.squadstack.parking.command.Args;


import com.squadstack.parking.enums.ParkingLotEnums.ArgsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @AllArgsConstructor @Getter
public class FetchSlotNumberCarRegQueryArgs implements ICommandArgs{

    private String regNo;


    @Override
    public ArgsType getArgsForType() {
        return ArgsType.COMMAND;
    }
}
