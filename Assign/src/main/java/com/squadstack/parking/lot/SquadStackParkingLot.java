package com.squadstack.parking.lot;
import com.squadstack.parking.enums.ParkingLotEnums.LotState;

public class SquadStackParkingLot extends BaseParkingLot{


    public SquadStackParkingLot(){
        super();
    }

    public SquadStackParkingLot(int slotSize, LotState lotRunningState){
        super(slotSize, lotRunningState);
    }


}
