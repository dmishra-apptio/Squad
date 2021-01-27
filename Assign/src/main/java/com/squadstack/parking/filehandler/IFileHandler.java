package com.squadstack.parking.filehandler;

import com.squadstack.parking.command.Command;
import com.squadstack.parking.lot.IParkingLot;

import java.io.IOException;
import java.util.Queue;

public interface IFileHandler {
    Queue<Command> readAndParse(String filePath, IParkingLot parkingLot) throws IOException;
    void write(String response, String filePath);
}
