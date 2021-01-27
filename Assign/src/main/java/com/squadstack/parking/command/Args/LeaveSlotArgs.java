package com.squadstack.parking.command.Args;

import com.squadstack.parking.enums.ParkingLotEnums.ArgsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class LeaveSlotArgs implements ICommandArgs {

    private Integer slotNumber;


    @Override
    public ArgsType getArgsForType() {
        return ArgsType.COMMAND;
    }
}
