package com.squadstack.parking.command;

import com.squadstack.parking.exceptions.SlotAlreadyAvailableException;
import com.squadstack.parking.lot.IParkingLot;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class TransactionCommand extends Command {

    private IParkingLot parkingLot;
    Queue<Command> queuedRequest;

    public TransactionCommand(IParkingLot parkingLot, Queue<Command> queuedRequest) {
        super(parkingLot);
        this.queuedRequest = queuedRequest;
    }

    @Override
    public String execute() throws SlotAlreadyAvailableException {
        System.out.println("******** STARTED PROCESSING COMMAND ********");
        StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
        if(queuedRequest!=null && queuedRequest.size()<1){
            System.out.print("Nothing to process");
        }
        while(!queuedRequest.isEmpty()){
            Command command = queuedRequest.poll();
            builder.append(command.execute());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    @Override
    public String description() {
        return "Runs all transaction command";
    }
}
