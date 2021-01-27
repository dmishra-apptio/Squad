package com.squadstack.parking.exceptions;

public class SlotAlreadyAvailableException extends RuntimeException {

    public SlotAlreadyAvailableException(String s) {
        super(s);
    }
}
