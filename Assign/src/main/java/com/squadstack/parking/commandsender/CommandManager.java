package com.squadstack.parking.commandsender;

import com.squadstack.parking.command.Command;
import com.squadstack.parking.enums.ParkingLotEnums.FileType;
import com.squadstack.parking.exceptions.SlotAlreadyAvailableException;
import com.squadstack.parking.filehandler.IFileHandler;
import lombok.AllArgsConstructor;

import java.nio.file.Paths;

@AllArgsConstructor
public class CommandManager {

    private Command command;
    String outFilePath;

    public CommandManager(Command command) {
        this.command = command;
    }

    public void driveCommand() throws SlotAlreadyAvailableException {
        String result = command.execute();
        writeOutputIntoFile(result);
    }

    public void writeOutputIntoFile(String response){
        String outFileName = Paths.get(outFilePath).getFileName().toString();
        String outExtension = outFileName.substring(outFileName.indexOf(".")+1).toUpperCase();
        IFileHandler fileHandler = FileType.valueOf(outExtension).getFileHandlerInstance();
        System.out.println("***** Generating Output @- "+ outFilePath);
        fileHandler.write(response, outFilePath);
    }


}
