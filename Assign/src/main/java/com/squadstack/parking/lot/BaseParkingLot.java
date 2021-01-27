package com.squadstack.parking.lot;

import com.squadstack.parking.command.Args.CreateParkingLotArgs;
import com.squadstack.parking.command.Args.DriverAgeArgs;
import com.squadstack.parking.command.Args.FetchAllSlotNumberDriverAgeArgs;
import com.squadstack.parking.command.Args.FetchAllVehicleRegNoForDriverAgeArgs;
import com.squadstack.parking.command.Args.FetchSlotNumberCarRegQueryArgs;
import com.squadstack.parking.command.Args.ICommandArgs;
import com.squadstack.parking.command.Args.LeaveSlotArgs;
import com.squadstack.parking.command.Args.ParkArgs;
import com.squadstack.parking.enums.ParkingLotEnums.LotState;
import com.squadstack.parking.enums.ParkingLotEnums.SlotState;
import com.squadstack.parking.exceptions.SlotAlreadyAvailableException;
import com.squadstack.parking.model.Slot;
import com.squadstack.parking.model.Vehicle;
import com.squadstack.parking.slotops.ISlotCollection;
import com.squadstack.parking.slotops.SlotCollectionImpl;
import com.squadstack.parking.utils.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public abstract class BaseParkingLot implements IParkingLot {

    private Integer slotSize;
    private LotState lotRunningState;
    private ISlotCollection slots;
    private Queue<Integer> nextFreeSlot;

    public BaseParkingLot() {
        this(null, LotState.NOT_ACTIVE);
    }

    public BaseParkingLot(Integer slotSize, LotState lotState) {
        this.slotSize = slotSize;
        this.lotRunningState = lotState;
        this.slots = new SlotCollectionImpl();
        this.nextFreeSlot = new PriorityQueue<>();
    }


    @Override
    public Integer parkVehicle(ICommandArgs commandArgs) {
        ParkArgs args = (ParkArgs) commandArgs;
        DriverAgeArgs driverAgeArgs = (DriverAgeArgs) args.getDriverAge();
        Integer nextNearestSlot = this.removeFromFreeSlot();
        Slot slot = slots.getSlot(nextNearestSlot);
        slots.updateSlot(slot, SlotState.OCCUPIED,new Vehicle(
            args.getVehicleRegNo(), driverAgeArgs.getDriverAge()));
        return nextNearestSlot;
    }

    @Override
    public Vehicle unParkVehicle(ICommandArgs commandArgs) throws SlotAlreadyAvailableException {
        LeaveSlotArgs args = (LeaveSlotArgs) commandArgs;
        Slot slot = slots.getSlot(args.getSlotNumber());
        if (slot.getSlotState() == SlotState.FREE) {
            throw new SlotAlreadyAvailableException(
                String.format("Slot is already vacant - %s", slot.getNumber()));
        }
        Vehicle vehicle = null;
        if (slot.getSlotState() == SlotState.OCCUPIED) {
            vehicle = slot.getVehicle();
            slots.updateSlot(slot, SlotState.FREE, null);
        }
        populateToFreeSlot(slot);
        return vehicle;
    }

    @Override
    public void createParkingSlot(ICommandArgs commandArgs) {
        CreateParkingLotArgs createParkingLotArgs = (CreateParkingLotArgs) commandArgs;
        this.slotSize = createParkingLotArgs.getSlotSize();
        this.lotRunningState = LotState.ACTIVE;
        for(int i =0;i<this.slotSize;i++){
            Slot slot = new Slot(SlotState.FREE,i+1);
            this.slots.addSlot(slot);
            this.populateToFreeSlot(slot);
        }
    }

    @Override
    public List<Integer> getAllVehicleRegNo(ICommandArgs commandArgs) {
        FetchAllVehicleRegNoForDriverAgeArgs args =
            (FetchAllVehicleRegNoForDriverAgeArgs) commandArgs;
        List<Slot> slotList = slots.getAllSlot();
        if(slotList.isEmpty()){
            return Collections.emptyList();
        }
        return slotList.stream()
            .filter(slot -> slot.getSlotState()==SlotState.OCCUPIED)
            .filter(slot -> slot.getNumber() == args.getDriverAge())
            .map(Slot::getNumber).collect(Collectors.toList());
    }

    @Override
    public Slot getSlotNo(ICommandArgs commandArgs) {
        FetchSlotNumberCarRegQueryArgs args = (FetchSlotNumberCarRegQueryArgs) commandArgs;
        List<Slot> slotList = slots.getAllSlot();
        return slotList.stream()
            .filter(slot -> Objects.nonNull(slot.getVehicle()))
            .filter(slot -> Objects.equals(slot.getVehicle().getRegNo(), args.getRegNo()))
            .findFirst().orElse(null);
    }

    @Override
    public String getSlotsNo(ICommandArgs commandArgs) {
        FetchAllSlotNumberDriverAgeArgs args = (FetchAllSlotNumberDriverAgeArgs) commandArgs;
        List<Slot> slotList = slots.getAllSlot();
        List<String> res =  slotList.stream()
            .filter(slot -> Objects.nonNull(slot.getVehicle()))
            .filter(slot -> slot.getVehicle().getDriverAge() == args.getDriverAge())
            .map(slot -> String.valueOf(slot.getNumber()))
            .collect(Collectors.toList());
        if (res.isEmpty()) {
            return StringUtils.EMPTY;
        }
        return res.stream().collect(Collectors.joining(Constants.COMMA));
    }

    protected void populateToFreeSlot(Slot slot){
        this.nextFreeSlot.add(slot.getNumber());
    }

    protected Integer removeFromFreeSlot(){
        return this.nextFreeSlot.poll();
    }
}
