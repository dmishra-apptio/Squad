package com.squadstack.parking.filehandler;

import com.squadstack.parking.command.Command;
import com.squadstack.parking.command.CommandFactory;
import com.squadstack.parking.exceptions.InvalidCommandException;
import com.squadstack.parking.lot.IParkingLot;
import com.squadstack.parking.utils.PropsLoader;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TextFileHandler implements IFileHandler {

    @Override
    public Queue<Command> readAndParse(String filePath, IParkingLot parkingLot) throws IOException {
        Queue<Command> request = new LinkedList<>();
        readFile(request, filePath, parkingLot);
        return request;
    }

    @Override
    public void write(String response, String filePath) {
        try {
            Files.write(Paths.get(filePath), response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(Queue<Command> commands, String filePath, IParkingLot parkingLot) throws IOException {
        try(Scanner scanner = new Scanner(new File(filePath))){
            System.out.println("***** Started Parsing Input File @loc -" + filePath);
            int lineCount = 1;
            while (scanner.hasNextLine()) {
                Command command = parseOntoCommand(scanner.nextLine(), parkingLot);
                commands.add(Objects.requireNonNull(command, "\nCommand Not found while processing"
                    + "file at line no:-" + lineCount));
                lineCount++;
            }
        }

        catch (FileNotFoundException | IllegalAccessException |
            InstantiationException | NullPointerException exception) {
            System.out.print(exception.getMessage());
        }
    }

    private<T extends Command> Command parseOntoCommand(
        String currentLine, IParkingLot parkingLot) throws IOException, IllegalAccessException, InstantiationException {
        StringTokenizer tokens = new StringTokenizer(currentLine);
        boolean isStartTokenCommand = false;
        List<Object> args = new ArrayList<>();
        PropsLoader propsLoader = PropsLoader.getInstance();
        String commandToken = StringUtils.EMPTY;
        while (tokens.hasMoreTokens()){
            Object token = tokens.nextToken();
            if (!isStartTokenCommand){
                isStartTokenCommand = true;
                if(!propsLoader.isValidCommand(String.valueOf(token)))
                    throw new InvalidCommandException(String.format(
                        "Not a valid command %s",token.toString()));
                commandToken = String.valueOf(token);
            }
            else if (propsLoader.checkOptionalParam(String.valueOf(token))){
                System.out.println("Got a Optional param - " + token.toString());
            }
            else {
                args.add(token);
            }
        }
        return CommandFactory.getCommand(commandToken, parkingLot, args);
    }
}
