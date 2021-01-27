package com.squadstack.parking.client;

import com.squadstack.parking.command.Command;
import com.squadstack.parking.command.TransactionCommand;
import com.squadstack.parking.commandsender.CommandManager;
import com.squadstack.parking.enums.ParkingLotEnums.FileType;
import com.squadstack.parking.filehandler.IFileHandler;
import com.squadstack.parking.lot.IParkingLot;
import com.squadstack.parking.lot.SquadStackParkingLot;
import com.squadstack.parking.utils.PropsLoader;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;

public class SquadClient {

    public static void main(String args[]) throws IOException {
        String filePath = System.getProperty("filePath");
        System.out.println(filePath);
        String outPath = System.getProperty("output");
        System.out.println(outPath);
        if (StringUtils.isEmpty(filePath) && StringUtils.isEmpty(outPath)){
            throw new FileNotFoundException("please provide filepath");
        }
        new SquadClient().performCommand(filePath, outPath);
    }

    public void performCommand(String filePath, String outPath) throws IOException {
        Path path = Paths.get(filePath);
        String[] token = path.getFileName().toString().split("\\.");
        String extns = StringUtils.EMPTY;
        if (token.length > 1){
            extns = token[1];
        }
        PropsLoader propsLoader = PropsLoader.getInstance();
        try {
            // initializing a parking lot
            IParkingLot parkingLot = new SquadStackParkingLot();
            // get file extension
            FileType fileType = FileType.valueOf(extns.toUpperCase());
            // based on file extension creating handler instance
            IFileHandler fileHandler = fileType.getFileHandlerInstance();
            // read the input file and convert into Queue of Commands
            Queue<Command> commands = fileHandler.readAndParse(filePath, parkingLot);
            // this drives all the command
            CommandManager commandManager = new CommandManager(
                // Transaction command has all the queue commmand and execute it one by one
                new TransactionCommand(parkingLot, commands), outPath
            );
            // actual invocation happens
            commandManager.driveCommand();
        } catch (IllegalArgumentException | NullPointerException | IOException exception) {
            System.out.print(exception.getMessage());
        }
    }
}
