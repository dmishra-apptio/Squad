package com.squadstack.parking.model;
import com.squadstack.parking.enums.ParkingLotEnums.SlotState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Slot {

    private int number;
    private SlotState slotState;
    private Vehicle vehicle;

    public Slot(SlotState state, int number) {
        this(state, number, null);
    }

    public Slot(SlotState state, int number, Vehicle vehicle) {
        this.slotState = state;
        this.number = number;
        this.vehicle = vehicle;
    }


}
