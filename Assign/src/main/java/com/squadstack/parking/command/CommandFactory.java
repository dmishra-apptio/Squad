package com.squadstack.parking.command;
import static com.squadstack.parking.utils.Constants.CREATE_PARKING_LOT;
import static com.squadstack.parking.utils.Constants.PARK;
import static com.squadstack.parking.utils.Constants.SLOT_NUMBERS_FOR_DRIVER_AGE;
import static com.squadstack.parking.utils.Constants.SLOT_NUMBER_FOR_REG_NO;
import com.squadstack.parking.command.Args.CreateParkingLotArgs;
import com.squadstack.parking.command.Args.DriverAgeArgs;
import com.squadstack.parking.command.Args.FetchAllSlotNumberDriverAgeArgs;
import com.squadstack.parking.command.Args.FetchAllVehicleRegNoForDriverAgeArgs;
import com.squadstack.parking.command.Args.FetchSlotNumberCarRegQueryArgs;
import com.squadstack.parking.command.Args.LeaveSlotArgs;
import com.squadstack.parking.command.Args.ParkArgs;
import com.squadstack.parking.lot.IParkingLot;
import com.squadstack.parking.utils.Constants;

import java.util.List;
import java.util.Objects;

public class CommandFactory {

    public static Command getCommand(String token,
        IParkingLot parkingLot, List<? extends Object> argsList) throws InstantiationException {
        switch (token){
            case PARK:
                if(Objects.nonNull(argsList) && argsList.size()>1){
                    return  new ParkCommand(parkingLot, new ParkArgs((String) argsList.get(0),
                        new DriverAgeArgs(Integer.parseInt((String) argsList.get(1)))));
                }
                break;

            case CREATE_PARKING_LOT:
                if(Objects.nonNull(argsList) && argsList.size() > 0){
                    return  new CreateParkingLotCommand(
                        parkingLot, new CreateParkingLotArgs(Integer.parseInt(
                            argsList.get(0).toString())));
                }
                break;
            case SLOT_NUMBER_FOR_REG_NO:
                if(Objects.nonNull(argsList) && argsList.size() > 0){
                    return  new FetchSlotNumberCarRegQueryCommand<FetchSlotNumberCarRegQueryArgs>(
                        parkingLot, new FetchSlotNumberCarRegQueryArgs(String.valueOf(
                            argsList.get(0))));
                }
                break;
            case SLOT_NUMBERS_FOR_DRIVER_AGE:
                if(Objects.nonNull(argsList) && argsList.size() > 0){
                    return  new FetchAllSlotNumberDriverAgeCommand(
                        parkingLot, new FetchAllSlotNumberDriverAgeArgs(
                            Integer.parseInt((String) argsList.get(0))));
                }
                break;
            case Constants.VEHICLE_REG_NO_FOR_DRIVER_AGE:
                if(Objects.nonNull(argsList) && argsList.size() > 0){
                    return  new FetchAllVehicleRegNoForDriverAgeCommand(
                        parkingLot, new FetchAllVehicleRegNoForDriverAgeArgs(
                        Integer.parseInt((String) argsList.get(0))));
                }
                break;
            case Constants.LEAVE:
                if(Objects.nonNull(argsList) && argsList.size() > 0){
                    return  new LeaveSlotCommand(
                        parkingLot, new LeaveSlotArgs(
                        Integer.parseInt((String) argsList.get(0))));
                }
                break;
            default:
                return null;

        }
        throw new InstantiationException("Some args are missing..");
    }

}
