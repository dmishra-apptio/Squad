package com.squadstack.parking.exceptions;

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(String s) {
        super(s);
    }
}
