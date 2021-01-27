package com.squadstack.parking.client;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.squadstack.parking.exceptions.InvalidCommandException;
import com.squadstack.parking.exceptions.SlotAlreadyAvailableException;
import org.junit.jupiter.api.Test;
import utils.TestConstants;

import java.io.IOException;

class SquadClientTest {

    @Test
    void testEntireParkingFlow() throws IOException {
        String inputFilePath = TestConstants.IN_DIR + "Squad_input.txt";
        String outFilePath = TestConstants.OUT_DIR + "out.txt";
        SquadClient client = new SquadClient();
        client.performCommand(inputFilePath, outFilePath);
    }

    @Test
    void testEntireParkingFlow_slotAlreadyVacant() {
        assertThrows(SlotAlreadyAvailableException.class, () -> {
            String inputFilePath = TestConstants.IN_DIR + "slot_already_vacant.txt";
            String outFilePath = TestConstants.OUT_DIR + "out_slot_already_vacant.txt";
            SquadClient client = new SquadClient();
            client.performCommand(inputFilePath, outFilePath);
        });
    }

    @Test
    void testEntireParkingFlow_CommandNotAvailable() {
        assertThrows(InvalidCommandException.class, () -> {
            String inputFilePath = TestConstants.IN_DIR + "command_not_found.txt";
            String outFilePath = TestConstants.OUT_DIR + "out_command_not_found.txt";
            SquadClient client = new SquadClient();
            client.performCommand(inputFilePath, outFilePath);
        });
    }



}